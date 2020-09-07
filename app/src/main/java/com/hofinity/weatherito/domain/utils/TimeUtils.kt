package com.hofinity.weatherito.domain.utils

import com.hofinity.weatherito.domain.utils.extentions.shiftToRight
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class TimeUtils {

    val hours: ArrayList<String> = arrayListOf("1AM", "2AM", "3AM", "4AM", "5AM", "6AM",
        "7AM", "8AM", "9AM", "10AM", "11AM", "12PM", "1PM", "2PM", "3PM", "4PM", "5PM", "6PM",
        "7PM", "8PM", "9PM", "10PM", "11PM", "12AM")

    private val currentHour: String = SimpleDateFormat("ha", Locale.getDefault()).format(Date())
    private val currentHourPosition: Int = hours.indexOf(currentHour)

    fun shiftedDayHours(position: Int = currentHourPosition): ArrayList<String> =
        hours.shiftToRight(hours.size - position-1)

//        val dayHours: ArrayList<String> = hours.shiftToRight(hours.size - hours.indexOf(currentHour)-1)

    fun convertTimes(currentTime: String,currentTimePattern: String,convertedTimePattern: String) : Int {
        var currentTimeInt = -1
        val date12Format = SimpleDateFormat(currentTimePattern, Locale.getDefault())
        val date24Format = SimpleDateFormat(convertedTimePattern, Locale.getDefault())
        try {
            date12Format.parse(currentTime)?.let {
                currentTimeInt = date24Format.format(it).toInt()
            }
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return currentTimeInt
    }
}