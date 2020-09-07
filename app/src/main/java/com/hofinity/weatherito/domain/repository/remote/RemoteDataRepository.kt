package com.hofinity.weatherito.domain.repository.remote

import com.hofinity.weatherito.domain.usecase.base.NetworkResource
import com.hofinity.weatherito.data.remote.RemoteForecastData
import com.hofinity.weatherito.domain.entity.forecast.CurrentForecastResponse
import com.hofinity.weatherito.domain.entity.forecast.GeneralForecastResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 *  @author Smooke
 */

class RemoteDataRepository @Inject
constructor(private val remoteRepository: RemoteForecastData): RemoteDataRepositorySource {

    override suspend fun requestCurrentForecast(id: String, appid: String): Flow<NetworkResource<CurrentForecastResponse>> {
        return flow {
//                emit(Resource.Loading())
            emit(remoteRepository.requestCurrentForecast(id,appid))
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun requestGeneralForecast(lat: String,lng: String,exclude: String, appid: String): Flow<NetworkResource<GeneralForecastResponse>> {
        return flow {
            emit(remoteRepository.requestGeneralForecast(lat,lng,exclude,appid))
        }.flowOn(Dispatchers.IO)
    }
}