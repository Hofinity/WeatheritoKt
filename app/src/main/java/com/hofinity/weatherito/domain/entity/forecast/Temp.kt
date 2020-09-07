package com.hofinity.weatherito.domain.entity.forecast

import com.google.gson.annotations.SerializedName

data class Temp (
    @SerializedName("day") var day: Double = 0.0,
    @SerializedName("min") var min: Double = 0.0,
    @SerializedName("max") var max: Double = 0.0,
    @SerializedName("morn") var morn: Double = 0.0,
    @SerializedName("eve") var eve: Double = 0.0,
    @SerializedName("night") var night: Double = 0.0
)

//@JsonClass(generateAdapter = true)
//@Parcelize
//data class Temp (
//    @Json(name = "day")
//    var day: Double = 0.0,
//
//    @Json(name = "min")
//    var min: Double = 0.0,
//
//    @Json(name = "max")
//    var max: Double = 0.0,
//
//    @Json(name = "morn")
//    var morn: Double = 0.0,
//
//    @Json(name = "eve")
//    var eve: Double = 0.0,
//
//    @Json(name = "night")
//    var night: Double = 0.0
//
//) : Parcelable