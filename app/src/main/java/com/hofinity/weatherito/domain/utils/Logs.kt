package com.hofinity.weatherito.domain.utils

import android.util.Log
import com.hofinity.weatherito.BuildConfig
import com.hofinity.weatherito.domain.utils.extentions.then

/**
 *  @author Smooke
 */

class Logs {
    companion object INSTANCE {
        fun d(tag: String, massage: String)  = (BuildConfig.DEBUG) then Log.d(tag, massage)
        fun i(tag: String, massage: String) = (BuildConfig.DEBUG) then Log.i(tag, massage)
        fun v(tag: String, massage: String) = (BuildConfig.DEBUG) then Log.v(tag, massage)
        fun e(tag: String, massage: String) = (BuildConfig.DEBUG) then Log.e(tag, massage)
    }
}
