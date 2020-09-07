package com.hofinity.weatherito.domain.utils.extentions

infix fun <T> Boolean.then(param: T): T? = if (this) param else null
//infix fun <T:Any> Boolean.then(param: T): T? = if (this) param else null