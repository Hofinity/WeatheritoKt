package com.hofinity.weatherito.domain.usecase.errors

import com.hofinity.weatherito.data.error.Error

interface ErrorFactory {
    fun getError(errorCode: Int): Error
}