package com.hofinity.weatherito.data.remote

import com.hofinity.weatherito.domain.usecase.base.NetworkResource
import com.hofinity.weatherito.domain.entity.forecast.CurrentForecastResponse
import com.hofinity.weatherito.domain.entity.forecast.GeneralForecastResponse

/**
 *  @author Smooke
 */

internal interface RemoteForecastDataSource {
    suspend fun requestCurrentForecast(id: String, appid: String): NetworkResource<CurrentForecastResponse>
    suspend fun requestGeneralForecast(lat: String, lng: String, exclude: String, appid: String): NetworkResource<GeneralForecastResponse>
}