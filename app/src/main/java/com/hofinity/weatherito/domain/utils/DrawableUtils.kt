package com.hofinity.weatherito.domain.utils

import com.hofinity.weatherito.R

class DrawableUtils {

    companion object {

        fun timeDrawable(currentTimeInt: Int): Int {
            return when (currentTimeInt) {
                in 0..6 -> R.drawable.trans_night_to_dawn
                in 7..11 -> R.drawable.trans_dawn_to_morning
                in 12..16 -> R.drawable.trans_morning_to_noon
                in 17..19 -> R.drawable.trans_noon_to_evening
                in 20..23 -> R.drawable.trans_evening_to_night
                else -> R.drawable.trans_dawn_to_morning
            }
        }

        fun getWeatherDrawableId(currentTimeInt: Int, weatherTitle: String?): Int {
            return when (weatherTitle) {
                "Clear" -> {
                    when (currentTimeInt) {
                        in 0..6 -> R.drawable.ic_clear_sky_dawn
                        in 7..11 -> R.drawable.ic_clear_sky_morning
                        in 12..16 -> R.drawable.ic_clear_sky_morning
                        in 17..19 -> R.drawable.ic_clear_sky_evening
                        in 20..23 -> R.drawable.ic_clear_sky_night
                        else -> R.drawable.ic_clear_sky_morning
                    }
                }
                "Clouds" -> {
                    when (currentTimeInt) {
                        in 0..6 -> R.drawable.ic_few_cloud_dawn
                        in 7..11 -> R.drawable.ic_few_cloud_morning
                        in 12..16 -> R.drawable.ic_few_cloud_morning
                        in 17..19 -> R.drawable.ic_few_cloud_evening
                        in 20..23 -> R.drawable.ic_few_cloud_evening
                        else -> R.drawable.ic_few_cloud_morning
                    }
                }
                "Rain" -> R.drawable.ic_shower_raint
                "Snow" -> R.drawable.ic_snow
                else -> R.drawable.ic_clear_sky_morning
            }
        }

        fun getWeatherDrawableId(currentTime: String, weatherTitle: String?): Int {
            return getWeatherDrawableId(TimeUtils().convertTimes(currentTime,
                "hha","HH"), weatherTitle)
        }
    }
}