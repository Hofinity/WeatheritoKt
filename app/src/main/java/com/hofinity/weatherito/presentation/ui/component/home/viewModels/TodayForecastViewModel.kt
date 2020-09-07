package com.hofinity.weatherito.presentation.ui.component.home.viewModels

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.hofinity.weatherito.data.error.mapper.ErrorMapper
import com.hofinity.weatherito.domain.app.App
import com.hofinity.weatherito.domain.entity.forecast.CurrentForecastResponse
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

class TodayForecastViewModel  @Inject
constructor(private val remoteDataRepositorySource: RemoteDataRepositorySource) : BaseViewModel() {

    override val errorManager: ErrorManager
        get() = ErrorManager(ErrorMapper())

    @VisibleForTesting
    val mCurrentForecastResponse = MutableLiveData<NetworkResource<CurrentForecastResponse>>()
    val currentForecastResponse: LiveData<NetworkResource<CurrentForecastResponse>> get() = mCurrentForecastResponse

    private val showToastPrivate = MutableLiveData<Event<Any>>()
    val showToast: LiveData<Event<Any>> get() = showToastPrivate

    fun retryLoadingCurrentTempReq() = currentTempReq(App.prefManager.cityID)

    fun currentTempReq(id: String) {
        App.prefManager.cityID = id
        viewModelScope.launch {
            mCurrentForecastResponse.value = NetworkResource.Loading()

            wrapEspressoIdlingResource {
                remoteDataRepositorySource.requestCurrentForecast(id, Constants.openWeatherMap_Key)
                    .collect {
                        mCurrentForecastResponse.value = it
                    }
            }
        }
    }

    fun showToastMessage(errorCode: Int) {
        val error = errorManager.getError(errorCode)
        showToastPrivate.value = Event(error.description)
    }
}