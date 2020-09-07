package com.hofinity.weatherito.domain.entity.forecast

import com.google.gson.annotations.SerializedName
import java.util.*

data class CurrentForecastResponse (

    @SerializedName("weather") var weather: ArrayList<Weather>,
    @SerializedName("main") var main: Main,
    @SerializedName("id") var id: Int = 0,
    @SerializedName("name") var name: String = "",
    @SerializedName("cod") var cod: Int = 0,
)