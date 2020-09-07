package com.hofinity.weatherito.domain.app.preferences

object PreferenceKeys {

        const val ROOT_PREFERENCES: String = "com.hofinity.weatherito"

        const val DefaultCityName = "Tehran"
        const val DefaultCityId = "112931"
        const val DefaultCityLat = "35.694389"
        const val DefaultCityLng = "51.421509"

        //        const val PREF_NAME = "Weatherito"
        const val CITY_NAME = "${ROOT_PREFERENCES}.cityName"
        const val CITY_ID = "${ROOT_PREFERENCES}.cityID"
        const val CITY_LAT = "${ROOT_PREFERENCES}.cityLat"
        const val CITY_LNG = "${ROOT_PREFERENCES}.cityLng"
        const val CURRENT_TIME_DRAWABLE = "${ROOT_PREFERENCES}.currentTimeDrawable"
}