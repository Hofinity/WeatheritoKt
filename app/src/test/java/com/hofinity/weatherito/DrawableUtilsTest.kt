package com.hofinity.weatherito

import com.hofinity.weatherito.domain.utils.DrawableUtils
import com.hofinity.weatherito.domain.utils.TimeUtils
import org.junit.Test

class DrawableUtilsTest {
    @Test
    fun timeDrawable() {
        val time = 5
        val timeDrawable = DrawableUtils.timeDrawable(time)

        println("time : $time")
        println("drawable : $timeDrawable")
    }

    @Test
    fun weatherDrawableId() {

        val currentTime = "5PM"
        val currentTimePattern = "hha"
        val convertedTimePattern = "HH"
        val convertTimes = TimeUtils().convertTimes(currentTime,currentTimePattern,convertedTimePattern)

        println("convertedTimePattern : $convertTimes")

        val drawableId = 22
        val weatherTitle = "clouds"
        val weatherDrawableId1 = DrawableUtils.getWeatherDrawableId(drawableId,weatherTitle)

        println("drawable id : $drawableId")
        println("weather title : $weatherTitle")
        println("weatherDrawableId1 : $weatherDrawableId1")

        val weatherDrawableId2 = DrawableUtils.getWeatherDrawableId(convertTimes,weatherTitle)

        println("weatherDrawableId2 : $weatherDrawableId2")

    }
}