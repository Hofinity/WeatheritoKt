package com.hofinity.weatherito.domain.repository.remote

import com.hofinity.weatherito.domain.usecase.base.NetworkResource
import com.hofinity.weatherito.domain.entity.forecast.CurrentForecastResponse
import com.hofinity.weatherito.domain.entity.forecast.GeneralForecastResponse
import kotlinx.coroutines.flow.Flow

/**
 *  @author Smooke
 */

interface RemoteDataRepositorySource {
    suspend fun requestCurrentForecast(id: String, appid: String): Flow<NetworkResource<CurrentForecastResponse>>
    suspend fun requestGeneralForecast(lat: String, lng: String, exclude: String, appid: String): Flow<NetworkResource<GeneralForecastResponse>>
}