package com.hofinity.weatherito.presentation.ui.component.cityPicker.viewModels

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hofinity.weatherito.data.error.mapper.ErrorMapper
import com.hofinity.weatherito.domain.entity.city.CityEntity
import com.hofinity.weatherito.domain.usecase.errors.ErrorManager
import com.hofinity.weatherito.presentation.ui.base.BaseViewModel
import javax.inject.Inject

class CityPickerViewModel  @Inject
constructor() : BaseViewModel() {

    override val errorManager: ErrorManager
        get() = ErrorManager(ErrorMapper())

    companion object {
        val cityList = arrayListOf(
            CityEntity(1, "143127", "Arāk", "34.09174", "49.689159"),
            CityEntity(2, "143534", "Āmol", "36.469608", "52.350719"),
            CityEntity(3, "143083", "Ardabīl", "38.249802", "48.293301"),
            CityEntity(4, "134602", "Fereydūnkenār", "36.68642", "52.522549"),
            CityEntity(5, "132892", "Gorgān", "36.838661", "54.43475"),
            CityEntity(6, "1160939", "Iranshahr", "27.20245", "60.684761"),
            CityEntity(7, "418862", "Isfahan", "33.0", "52.166672"),
            CityEntity(8, "128747", "Karaj", "35.835499", "51.0103"),
            CityEntity(9, "128477", "Kashan", "33.983082", "51.43644"),
            CityEntity(10, "128226", "Kermanshah", "34.314171", "47.064999"),
            CityEntity(11, "127349", "Khorramabad", "33.487782", "48.355831"),
            CityEntity(12, "127319", "Khorramshahr", "30.439699", "48.166401"),
            CityEntity(13, "118743", "Rasht", "37.280769", "49.583191"),
            CityEntity(14, "115019", "Shiraz", "29.6036", "52.538799"),
            CityEntity(15, "114593", "Shūsh", "32.194199", "48.243599"),
            CityEntity(16, "114584", "Shūshtar", "32.045502", "48.856701"),
            CityEntity(17, "113646", "Tabriz", "38.080002", "46.291901"),
            CityEntity(18, "112931", "Tehran", "35.694389", "51.421509")
        )
    }

    @VisibleForTesting
    val mCities = MutableLiveData<ArrayList<CityEntity>>()
    val cities: LiveData<ArrayList<CityEntity>> get() = mCities

    init {
        mCities.value = cityList
    }
}