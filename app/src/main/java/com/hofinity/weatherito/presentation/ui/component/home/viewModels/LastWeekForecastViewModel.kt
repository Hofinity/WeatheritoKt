package com.hofinity.weatherito.presentation.ui.component.home.viewModels

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.hofinity.weatherito.data.error.mapper.ErrorMapper
import com.hofinity.weatherito.domain.app.App
import com.hofinity.weatherito.domain.entity.forecast.GeneralForecastResponse
import com.hofinity.weatherito.domain.repository.remote.RemoteDataRepositorySource
import com.hofinity.weatherito.domain.usecase.base.NetworkResource
import com.hofinity.weatherito.domain.usecase.errors.ErrorManager
import com.hofinity.weatherito.domain.utils.Constants
import com.hofinity.weatherito.domain.utils.Event
import com.hofinity.weatherito.domain.utils.wrapEspressoIdlingResource
import com.hofinity.weatherito.presentation.ui.base.BaseViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class LastWeekForecastViewModel  @Inject
constructor(private val remoteDataRepositorySource: RemoteDataRepositorySource) : BaseViewModel() {

    override val errorManager: ErrorManager
        get() = ErrorManager(ErrorMapper())

    @VisibleForTesting
    val mGeneralForecastResponse = MutableLiveData<NetworkResource<GeneralForecastResponse>>()
    val generalForecastResponse: LiveData<NetworkResource<GeneralForecastResponse>> get() = mGeneralForecastResponse

    private val mShowToast = MutableLiveData<Event<Any>>()
    val showToast: LiveData<Event<Any>> get() = mShowToast

    fun retryLoadingGeneralTempInfo(exclude:String = "minutely") = App.prefManager.let {
        generalTempInfoReq(it.cityLat, it.cityLng,exclude)
    }

    fun generalTempInfoReq(lat: String, lng: String, exclude: String = "minutely") {
        App.prefManager.let { it.cityLat = lat ; it.cityLng = lng }
        viewModelScope.launch {
            mGeneralForecastResponse.value = NetworkResource.Loading()

            wrapEspressoIdlingResource {
                remoteDataRepositorySource.requestGeneralForecast(lat,lng,exclude,Constants.openWeatherMap_Key
                ).collect {
                    mGeneralForecastResponse.value = it
                }
            }
        }
    }
}