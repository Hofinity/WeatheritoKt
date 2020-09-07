package com.hofinity.weatherito.domain.utils

import com.hofinity.weatherito.BuildConfig

/**
 *  @author Smooke
 */

class Constants {
    companion object INSTANCE {
        var ServerUrl = "https://api.openweathermap.org"
        var BASE_URL = "$ServerUrl/data/2.5/"
        const val openWeatherMap_Key: String = BuildConfig.OPENWEATHERMAP_KEY
    }
}
