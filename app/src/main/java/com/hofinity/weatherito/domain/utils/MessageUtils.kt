package com.hofinity.weatherito.domain.utils

import android.content.Context
import android.widget.Toast
import com.hofinity.weatherito.domain.app.App

class MessageUtils {


    fun toast(message: String,context: Context = App.context,  length: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(context, message, length).show()
    }
}