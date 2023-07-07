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
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object CoreModule {

    @Provides
    fun provideClient(): OkHttpClient {
        val clientBuilder = OkHttpClient.Builder()
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