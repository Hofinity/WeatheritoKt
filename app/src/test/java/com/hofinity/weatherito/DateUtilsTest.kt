package com.hofinity.weatherito

import com.hofinity.weatherito.domain.utils.DateUtils
import com.hofinity.weatherito.domain.utils.extentions.shiftToRight
import org.junit.Test

class DateUtilsTest {
    @Test
    fun weekDaysShifting() {
//        assertEquals(4, 2 + 2)

        val days = DateUtils().days
        println("week days : $days")

//        val currentDayPosition = CalendarUtils.currentDayPosition()
        val currentDayPosition = 5
        val currentDay = DateUtils().weekDay(currentDayPosition - 1)
        val shiftTimes = days.size - days.indexOf(currentDay) -1
        days.shiftToRight()

        println("day position on calendar : $currentDayPosition")
        println("current day : $currentDay")
        println("shift times : $shiftTimes")
        println("week days after shifting : $days")
    }
}