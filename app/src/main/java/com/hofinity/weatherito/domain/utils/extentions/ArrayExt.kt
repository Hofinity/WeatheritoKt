package com.hofinity.weatherito.domain.utils.extentions

import java.util.ArrayList

fun<T> ArrayList<T>.shiftToRight(shiftTimes: Int = 1): ArrayList<T> {

    repeat(shiftTimes) {
        val temp = this[this.size - 1]
        for (j in this.size - 1 downTo 1) {
            this[j] = this[j - 1]
        }
        this[0] = temp
    }
    return this
}