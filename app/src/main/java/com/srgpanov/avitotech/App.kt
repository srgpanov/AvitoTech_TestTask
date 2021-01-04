package com.srgpanov.avitotech

import android.app.Application
import com.srgpanov.avitotech.di.components.AppComponent
import com.srgpanov.avitotech.di.components.DaggerAppComponent
import com.srgpanov.avitotech.di.modules.AppModule

class App:Application() {

    lateinit var appComponent: AppComponent
        private set

    companion object {
        lateinit var instance: App
            private set
    }
    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent= DaggerAppComponent.builder()
            .application(this)
            .build()

    }
}