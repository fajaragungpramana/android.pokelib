package com.github.fajaragungpramana.pokelib.core.di

import androidx.room.Room
import com.github.fajaragungpramana.pokelib.core.BuildConfig
import com.github.fajaragungpramana.pokelib.core.data.favorite.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
object CoreModule {

    @Provides
    fun provideCertificate(): CertificatePinner =
        CertificatePinner.Builder()
            .add(BuildConfig.BASE_HOSTNAME, "sha256/9Yo5DIhM434OpBbqe2YOTJEt4+cTVf1OLs4mqhipC4o=")
            .add(BuildConfig.BASE_HOSTNAME, "sha256/81Wf12bcLlFHQAfJluxnzZ6Frg+oJ9PWY/Wrwur8viQ=")
            .add(BuildConfig.BASE_HOSTNAME, "sha256/hxqRlPTu1bMS/0DITB1SSu0vd4u/8l8TjPgfaAp63Gc=")
            .build()

    @Provides
    fun provideClient(certificate: CertificatePinner): OkHttpClient {
        val clientBuilder = OkHttpClient.Builder()
        clientBuilder.connectTimeout(30, TimeUnit.SECONDS)
        clientBuilder.readTimeout(30, TimeUnit.SECONDS)
        clientBuilder.certificatePinner(certificate)

        if (BuildConfig.DEBUG)
            clientBuilder.addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })

        return clientBuilder.build()
    }

    @Provides
    fun provideRestClient(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

    @Provides
    fun provideAppDatabase(): AppDatabase {
        val passphrase = SQLiteDatabase.getBytes(BuildConfig.SQL_KEY.toCharArray())
        val factory = SupportFactory(passphrase)

        return Room.databaseBuilder(
            Core.getContext(), AppDatabase::class.java, BuildConfig.SQL_DATABASE
        ).fallbackToDestructiveMigration().openHelperFactory(factory).build()
    }

}