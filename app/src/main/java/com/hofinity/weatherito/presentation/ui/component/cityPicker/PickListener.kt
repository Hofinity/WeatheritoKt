package com.hofinity.weatherito.presentation.ui.component.cityPicker

import com.hofinity.weatherito.domain.entity.city.CityEntity

interface PickListener {
    fun onFinish(city: CityEntity)
}