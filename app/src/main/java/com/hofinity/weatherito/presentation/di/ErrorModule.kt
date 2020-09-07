package com.hofinity.weatherito.presentation.di

import com.hofinity.weatherito.data.error.mapper.ErrorMapper
import com.hofinity.weatherito.data.error.mapper.ErrorMapperInterface
import com.hofinity.weatherito.domain.usecase.errors.ErrorFactory
import com.hofinity.weatherito.domain.usecase.errors.ErrorManager
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Suppress("unused")
@Module
abstract class ErrorModule {
    @Binds
    @Singleton
    abstract fun provideErrorFactoryImpl(errorManager: ErrorManager): ErrorFactory

    @Binds
    @Singleton
    abstract fun provideErrorMapper(errorMapper: ErrorMapper): ErrorMapperInterface
}
