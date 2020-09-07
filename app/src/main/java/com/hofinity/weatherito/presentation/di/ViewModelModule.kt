package com.hofinity.weatherito.presentation.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hofinity.weatherito.presentation.ui.component.cityPicker.viewModels.CityPickerViewModel
import com.hofinity.weatherito.presentation.ui.component.ViewModelFactory
import com.hofinity.weatherito.presentation.ui.component.home.viewModels.BackgroundViewModel
import com.hofinity.weatherito.presentation.ui.component.home.viewModels.HomeViewModel
import com.hofinity.weatherito.presentation.ui.component.home.viewModels.LastWeekForecastViewModel
import com.hofinity.weatherito.presentation.ui.component.home.viewModels.TodayForecastViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(viewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(BackgroundViewModel::class)
    abstract fun bindBackgroundViewModel(viewModel: BackgroundViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TodayForecastViewModel::class)
    abstract fun bindTodayForecastViewModel(viewModel: TodayForecastViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LastWeekForecastViewModel::class)
    abstract fun bindLastWeekForecastViewModel(viewModel: LastWeekForecastViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CityPickerViewModel::class)
    abstract fun bindCityPickerViewModel(viewModel: CityPickerViewModel): ViewModel
}
