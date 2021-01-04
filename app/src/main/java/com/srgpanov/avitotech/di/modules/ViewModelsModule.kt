package com.srgpanov.avitotech.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.huntworld.huntworld.di.ViewModelFactory
import com.srgpanov.avitotech.ui.screens.number_list.NumberViewModel
import com.srgpanov.simpleweather.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelsModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory


    @Binds
    @IntoMap
    @ViewModelKey(NumberViewModel::class)
    internal abstract fun numberViewModel(viewModel: NumberViewModel): ViewModel

}