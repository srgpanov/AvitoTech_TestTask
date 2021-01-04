package com.srgpanov.avitotech.di.modules

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule() {

    @Singleton
    @Provides
    fun provideApplication(app: Application): Application {
        return app
    }

    @Singleton
    @Provides
    fun provideContext(app: Application): Context {
        return app.applicationContext
    }


}