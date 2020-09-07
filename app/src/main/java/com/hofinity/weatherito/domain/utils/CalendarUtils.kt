package com.hofinity.weatherito.domain.utils

import java.util.*

class CalendarUtils {

    companion object {

        fun currentDayPosition(): Int {
            val calendar = Calendar.getInstance()
            calendar.time = Date()
            return calendar[Calendar.DAY_OF_WEEK]
        }
    }
}