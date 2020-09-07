package com.hofinity.weatherito.data.error.mapper

import com.hofinity.weatherito.R
import com.hofinity.weatherito.data.error.Error
import com.hofinity.weatherito.domain.app.App
import javax.inject.Inject

class ErrorMapper @Inject constructor() : ErrorMapperInterface {

    override fun getErrorString(errorId: Int): String {
        return App.context.getString(errorId)
    }

    override val errorsMap: Map<Int, String>
        get() = mapOf(
                Pair(Error.NO_INTERNET_CONNECTION, getErrorString(R.string.m_no_internet)),
                Pair(Error.NETWORK_ERROR, getErrorString(R.string.m_network_error))
        ).withDefault { getErrorString(R.string.m_network_error) }
}