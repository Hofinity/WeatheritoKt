package com.hofinity.weatherito.data.remote.service

import com.hofinity.weatherito.domain.entity.forecast.CurrentForecastResponse
import com.hofinity.weatherito.domain.entity.forecast.GeneralForecastResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ForecastServices {

    /**
     *
     * @param id city id
     * @param appid open weather map key
     *
     * @return Current weather of city based on city id
     */
    @GET("weather")
    suspend fun currentForecast(
        @Query("id") id: String,
        @Query("appid") appid: String
    ): Response<CurrentForecastResponse>


    /**
     *
     * @param lat latitude
     * @param lon longitude
     * @param appid open weather map key
     *
     * @return Current weather of city based on city LatLng
     */
    @GET("weather")
    suspend fun currentForecast(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("appid") appid: String
    ): Response<CurrentForecastResponse>


    /**
     *
     * @param lat latitude
     * @param lon longitude
     * @param exclude {minutely,hourly,daily}
     * @param appid open weather map key
     *
     * @return General weather of city based on city LatLng
     */
    @GET("onecall")
    suspend fun generalForecast(
        @Query("lat") lat: String?,
        @Query("lon") lon: String?,
        @Query("exclude") exclude: String?,
        @Query("appid") appid: String?
    ): Response<GeneralForecastResponse>
}