package com.hofinity.weatherito.domain.utils

import com.hofinity.weatherito.domain.utils.extentions.shiftToRight
import java.util.*

class DateUtils {

    private val currentDayPosition = CalendarUtils.currentDayPosition()

    val days = arrayListOf("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday")

    fun weekDay(position: Int = currentDayPosition): String  = days[position]

    fun shiftedWeekDays(position: Int = currentDayPosition): ArrayList<String> =
        days.shiftToRight(days.size - days.indexOf(weekDay(position-1)) - 1)

}