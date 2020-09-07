package com.hofinity.weatherito.presentation.di

import com.hofinity.weatherito.presentation.ui.component.home.HomeActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class ActivityModuleBuilder {
    @ContributesAndroidInjector()
    abstract fun contributeHomeActivity(): HomeActivity
}