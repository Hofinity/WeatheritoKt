package com.hofinity.weatherito.domain.entity.forecast

import com.google.gson.annotations.SerializedName
import java.util.*

data class Daily (
    @SerializedName("temp") var temp: Temp,
    @SerializedName("pressure") var pressure: Double = 0.0,
    @SerializedName("humidity") var humidity: Double = 0.0,
    @SerializedName("dew_point") var dewPoint: Double = 0.0,
    @SerializedName("wind_speed") var windSpeed: Double = 0.0,
    @SerializedName("weather") var weather: ArrayList<Weather>
)