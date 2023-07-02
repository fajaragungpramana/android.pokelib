package com.github.fajaragungpramana.pokelib

import android.app.Application
import com.github.fajaragungpramana.pokelib.core.di.Core
import com.github.fajaragungpramana.pokelib.di.AppComponent
import com.github.fajaragungpramana.pokelib.di.AppComponentProvider
import com.github.fajaragungpramana.pokelib.di.DaggerAppComponent
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application(), AppComponentProvider {

    private val mAppComponent: AppComponent = DaggerAppComponent.create()

    override fun provideAppComponent(): AppComponent = mAppComponent

    override fun onCreate() {
        super.onCreate()

        Core.init(applicationContext)
    }

}