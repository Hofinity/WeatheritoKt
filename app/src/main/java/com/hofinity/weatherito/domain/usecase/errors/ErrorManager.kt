package com.hofinity.weatherito.domain.usecase.errors

import com.hofinity.weatherito.data.error.Error
import com.hofinity.weatherito.data.error.mapper.ErrorMapper
import javax.inject.Inject

/**
 *  @author Smooke
 */

class ErrorManager @Inject constructor(private val errorMapper: ErrorMapper) : ErrorFactory {
    override fun getError(errorCode: Int): Error {
        return Error(code = errorCode, description = errorMapper.errorsMap.getValue(errorCode))
    }

}