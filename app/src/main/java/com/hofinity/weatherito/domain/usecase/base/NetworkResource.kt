package com.hofinity.weatherito.domain.usecase.base

sealed class NetworkResource<T>(val data: T? = null, val errorCode: Int? = null) {
    class NoConnection<T>(errorCode: Int) : NetworkResource<T>(null, errorCode)
    class Success<T>(data: T) : NetworkResource<T>(data)
    class Loading<T>(data: T? = null) : NetworkResource<T>(data)
    class DataError<T>(errorCode: Int) : NetworkResource<T>(null, errorCode)
}