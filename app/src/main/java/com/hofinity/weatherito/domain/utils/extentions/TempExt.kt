package com.hofinity.weatherito.domain.utils.extentions


fun Double.fahrenheitToCelsius(): Double = (this - 32.0) * 5.0 / 9.0

fun Double.celsiusToFahrenheit(): Double = (this * 9.0 / 5.0) + 32.0

fun Double.kelvinToCelsius(): Double = this - 273.15

fun Double.celsiusToKelvin(): Double = this + 273.15