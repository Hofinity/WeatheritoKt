package com.hofinity.weatherito.domain.entity.forecast

import com.google.gson.annotations.SerializedName
import java.util.*

data class GeneralForecastResponse (
    @SerializedName("hourly") var hourly: ArrayList<Hourly>,
    @SerializedName("daily") var daily: ArrayList<Daily>
)