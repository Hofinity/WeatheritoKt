package com.hofinity.weatherito.presentation.di

import com.hofinity.weatherito.domain.repository.remote.RemoteDataRepository
import com.hofinity.weatherito.domain.repository.remote.RemoteDataRepositorySource
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Suppress("unused")
@Module
abstract class DataModule {
    @Binds
    @Singleton
    abstract fun provideDataRepository(remoteDataRepository: RemoteDataRepository): RemoteDataRepositorySource
}
