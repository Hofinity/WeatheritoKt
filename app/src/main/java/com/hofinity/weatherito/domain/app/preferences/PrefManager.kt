package com.hofinity.weatherito.domain.app.preferences

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import com.hofinity.weatherito.R
import com.hofinity.weatherito.domain.app.preferences.PreferenceKeys.CITY_ID
import com.hofinity.weatherito.domain.app.preferences.PreferenceKeys.CITY_LAT
import com.hofinity.weatherito.domain.app.preferences.PreferenceKeys.CITY_LNG
import com.hofinity.weatherito.domain.app.preferences.PreferenceKeys.CITY_NAME
import com.hofinity.weatherito.domain.app.preferences.PreferenceKeys.CURRENT_TIME_DRAWABLE
import com.hofinity.weatherito.domain.app.preferences.PreferenceKeys.DefaultCityId
import com.hofinity.weatherito.domain.app.preferences.PreferenceKeys.DefaultCityLat
import com.hofinity.weatherito.domain.app.preferences.PreferenceKeys.DefaultCityLng
import com.hofinity.weatherito.domain.app.preferences.PreferenceKeys.DefaultCityName
import com.hofinity.weatherito.domain.app.preferences.PreferenceKeys.ROOT_PREFERENCES

class PrefManager @SuppressLint("CommitPrefEdits")
constructor(var context: Context) {

    private val pref: SharedPreferences
    private val editor: SharedPreferences.Editor

    init {
        val privateMode = 0
        pref = context.getSharedPreferences(ROOT_PREFERENCES, privateMode)
        editor = pref.edit()
    }

    var cityName: String
        get() = pref.getString(CITY_NAME, DefaultCityName).toString()
        set(cityName) {
            editor.putString(CITY_NAME, cityName)
            editor.commit()
        }

    var cityID: String
        get() = pref.getString(CITY_ID, DefaultCityId).toString()
        set(cityID) {
            editor.putString(CITY_ID, cityID)
            editor.commit()
        }

    var cityLat: String
        get() = pref.getString(CITY_LAT, DefaultCityLat).toString()
        set(cityLat) {
            editor.putString(CITY_LAT, cityLat)
            editor.commit()
        }

    var cityLng: String
        get() = pref.getString(CITY_LNG, DefaultCityLng).toString()
        set(cityLng) {
            editor.putString(CITY_LNG, cityLng)
            editor.commit()
        }

    var currentTimeDrawable: Int
        get() = pref.getInt(CURRENT_TIME_DRAWABLE, R.drawable.trans_morning_to_noon)
        set(currentTimeDrawable) {
            editor.putInt(CURRENT_TIME_DRAWABLE, currentTimeDrawable)
            editor.commit()
        }
}