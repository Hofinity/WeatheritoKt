package com.hofinity.weatherito.domain.entity.forecast

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import java.util.*

data class Hourly (
    @SerializedName("temp") var temp: Double = 0.0,
    @SerializedName("pressure") var pressure: Double = 0.0,
    @SerializedName("humidity") var humidity: Double = 0.0,
    @SerializedName("dew_point") var dewPoint: Double = 0.0,
    @SerializedName("wind_speed") var windSpeed: Double = 0.0,
    @Json(name = "weather") var weather: ArrayList<Weather>

)