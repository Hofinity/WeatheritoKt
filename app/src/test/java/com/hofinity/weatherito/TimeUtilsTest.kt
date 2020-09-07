package com.hofinity.weatherito

import com.hofinity.weatherito.domain.utils.TimeUtils
import org.junit.Test

class TimeUtilsTest {
    @Test
    fun timeDrawable() {

        println("time : ${TimeUtils().hours}")

        val currentHour = "5PM"
        val currentHourPosition: Int = TimeUtils().hours.indexOf(currentHour)
        val shiftedDayHours = TimeUtils().shiftedDayHours(currentHourPosition)

        println("current hour : $currentHour")
        println("current hour position : $currentHourPosition")
        println("shifted day hours : $shiftedDayHours")

    }

    @Test
    fun convertTimes() {

        val currentTime = "5PM"
        val currentTimePattern = "hha"
        val convertedTimePattern = "HH"

        val convertTimes = TimeUtils().convertTimes(currentTime,currentTimePattern,convertedTimePattern)

        println("convertedTimePattern : $convertTimes")

    }
}