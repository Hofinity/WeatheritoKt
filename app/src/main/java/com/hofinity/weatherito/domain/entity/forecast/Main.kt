package com.hofinity.weatherito.domain.entity.forecast

import com.google.gson.annotations.SerializedName

data class Main (
    @SerializedName("temp")
    var temp: Double = 0.0,

    @SerializedName("temp_min")
    var minTemp: Double = 0.0,

    @SerializedName("temp_max")
    var maxTemp: Double = 0.0
)


//@JsonClass(generateAdapter = true)
//@Parcelize
//data class Main (
//    @Json(name = "temp")
//    var temp: Double = 0.0,
//
//    @Json(name = "temp_min")
//    var minTemp: Double = 0.0,
//
//    @Json(name = "temp_max")
//    var maxTemp: Double = 0.0
//) : Parcelable