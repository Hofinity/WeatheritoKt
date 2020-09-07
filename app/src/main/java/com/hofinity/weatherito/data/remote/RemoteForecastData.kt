package com.hofinity.weatherito.data.remote

import com.hofinity.weatherito.domain.usecase.base.NetworkResource
import com.hofinity.weatherito.data.error.Error.Companion.NETWORK_ERROR
import com.hofinity.weatherito.data.error.Error.Companion.NO_INTERNET_CONNECTION
import com.hofinity.weatherito.data.remote.service.ForecastServices
import com.hofinity.weatherito.domain.app.App
import com.hofinity.weatherito.domain.entity.forecast.CurrentForecastResponse
import com.hofinity.weatherito.domain.entity.forecast.GeneralForecastResponse
import com.hofinity.weatherito.domain.utils.Network.Utils.isConnected
import com.hofinity.weatherito.domain.utils.extentions.then
import com.hofinity.weatherito.domain.utils.wrapEspressoIdlingResource
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

/**
 *  @author Smooke
 */

class RemoteForecastData @Inject
constructor(private val serviceGenerator: ServiceGenerator) : RemoteForecastDataSource {

    private val forecastServices = serviceGenerator.createService(ForecastServices::class.java)

    override suspend fun requestCurrentForecast(id: String, appid: String): NetworkResource<CurrentForecastResponse> {

        return (!isConnected(App.context)) then NetworkResource.NoConnection(NO_INTERNET_CONNECTION) ?:
        wrapEspressoIdlingResource {
            return when (val response = processCurrentForecastCall(id, appid, forecastServices::currentForecast)) {
                is CurrentForecastResponse -> NetworkResource.Success(data = response)
                else -> NetworkResource.DataError(errorCode = response as Int)
            }
        }
    }

    override suspend fun requestGeneralForecast(lat: String, lng: String, exclude: String,
                                                appid: String): NetworkResource<GeneralForecastResponse> {

        return (!isConnected(App.context)) then NetworkResource.NoConnection(NO_INTERNET_CONNECTION) ?:
        wrapEspressoIdlingResource {
            return when (val response = processGeneralForecastCall(
                lat,lng,exclude,appid, forecastServices::generalForecast)) {
                is GeneralForecastResponse -> NetworkResource.Success(data = response)
                else -> NetworkResource.DataError(errorCode = response as Int)
            }
        }
    }

    private suspend fun processCurrentForecastCall(id: String, appid:String,responseCall: suspend
                                                  (id: String, appid:String) -> Response<*>): Any? {

        return (!isConnected(App.context)) then NO_INTERNET_CONNECTION ?:
        try {
            val response = responseCall.invoke(id,appid)
            val responseCode = response.code()
            (response.isSuccessful) then response.body() ?: responseCode
        } catch (e: IOException) {
            NETWORK_ERROR
        }
    }

    private suspend fun processGeneralForecastCall(lat: String, lng: String, exclude: String, appid:String,
                                                   responseCall: suspend (lat: String, lng: String,
                                                   exclude: String, appid:String) -> Response<*>): Any? {

        return (!isConnected(App.context)) then NO_INTERNET_CONNECTION ?:
        try {
            val response = responseCall.invoke(lat,lng,exclude,appid)
            val responseCode = response.code()
            (response.isSuccessful) then response.body() ?: responseCode
        } catch (e: IOException) {
            NETWORK_ERROR
        }
    }
}