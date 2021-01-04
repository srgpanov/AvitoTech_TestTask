package com.srgpanov.avitotech.di.components

import android.app.Application
import com.srgpanov.avitotech.di.modules.AppModule
import com.srgpanov.avitotech.di.modules.ViewModelsModule
import com.srgpanov.avitotech.ui.screens.number_list.NumberFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        AppModule::class,
        ViewModelsModule::class
    ]
)
interface AppComponent {
    fun injectNumberFragment(fragment: NumberFragment)

    @Component.Builder
    interface Builder {
        fun build(): AppComponent

        @BindsInstance
        fun application(application: Application?): Builder
    }
}