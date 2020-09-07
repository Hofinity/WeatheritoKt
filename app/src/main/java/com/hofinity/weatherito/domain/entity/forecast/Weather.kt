package com.hofinity.weatherito.domain.entity.forecast

import com.google.gson.annotations.SerializedName

data class Weather (

    @SerializedName("main") var main: String = ""

)

//@JsonClass(generateAdapter = true)
//@Parcelize
//data class Weather (
//
//    @Json(name = "main")
//    var main: String = ""
//
//) : Parcelable