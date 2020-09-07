package com.hofinity.weatherito.presentation.ui.component.home

import android.graphics.drawable.Drawable
import android.graphics.drawable.TransitionDrawable
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.hofinity.weatherito.R
import com.hofinity.weatherito.domain.usecase.base.NetworkResource
import com.hofinity.weatherito.presentation.ui.base.BaseActivity
import com.hofinity.weatherito.domain.entity.city.CityEntity
import com.hofinity.weatherito.databinding.ActivityHomeBinding
import com.hofinity.weatherito.domain.app.App
import com.hofinity.weatherito.presentation.ui.component.ViewModelFactory
import com.hofinity.weatherito.domain.entity.forecast.CurrentForecastResponse
import com.hofinity.weatherito.domain.entity.forecast.Daily
import com.hofinity.weatherito.domain.entity.forecast.GeneralForecastResponse
import com.hofinity.weatherito.domain.entity.forecast.Hourly
import com.hofinity.weatherito.domain.utils.DrawableUtils
import com.hofinity.weatherito.domain.utils.EspressoIdlingResource
import com.hofinity.weatherito.domain.utils.Event
import com.hofinity.weatherito.domain.utils.extentions.*
import com.hofinity.weatherito.presentation.ui.component.cityPicker.CityPickerDialog.cityPickerDialog
import com.hofinity.weatherito.presentation.ui.component.cityPicker.viewModels.CityPickerViewModel
import com.hofinity.weatherito.presentation.ui.component.cityPicker.PickListener
import com.hofinity.weatherito.presentation.ui.component.home.adapters.lastWeekForecastAdapter.LastWeekForecastAdapter
import com.hofinity.weatherito.presentation.ui.component.home.adapters.todayForecastAdapter.TodayForecastAdapter
import com.hofinity.weatherito.presentation.ui.component.home.viewModels.BackgroundViewModel
import com.hofinity.weatherito.presentation.ui.component.home.viewModels.LastWeekForecastViewModel
import com.hofinity.weatherito.presentation.ui.component.home.viewModels.TodayForecastViewModel
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper
import javax.inject.Inject
import kotlin.collections.ArrayList
import kotlin.math.roundToInt

class HomeActivity : BaseActivity() {

    lateinit var bounce: LayoutAnimationController
    lateinit var slideUp: LayoutAnimationController

    private var generalWeatherResponsesDailyList = ArrayList<Daily>()
    private var generalWeatherResponsesHourlyList = ArrayList<Hourly>()

    private lateinit var behavior: BottomSheetBehavior<*>

    private var lastWeekForecastAdapter: LastWeekForecastAdapter? = null
    private var todayForecastAdapter: TodayForecastAdapter? = null

    private lateinit var binding: ActivityHomeBinding
    private lateinit var rootView: View

    @Inject
    lateinit var backgroundViewModel: BackgroundViewModel

    @Inject
    lateinit var todayForecastViewModel: TodayForecastViewModel

    @Inject
    lateinit var lastWeekForecastViewModel: LastWeekForecastViewModel

    @Inject
    lateinit var cityPickerViewModel: CityPickerViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun initializeViewModel() {
        backgroundViewModel = viewModelFactory.create(BackgroundViewModel::class.java)
        todayForecastViewModel = viewModelFactory.create(TodayForecastViewModel::class.java)
        lastWeekForecastViewModel = viewModelFactory.create(LastWeekForecastViewModel::class.java)
        cityPickerViewModel = viewModelFactory.create(CityPickerViewModel::class.java)
    }

    override fun observeViewModel() {
        observe(todayForecastViewModel.currentForecastResponse,::handleTodayForecastResponse)
        observe(lastWeekForecastViewModel.generalForecastResponse,::handleLastWeekForecastResponse)
        observe(backgroundViewModel.backgroundDrawable,::updateBackground)
//        observe(cityPickerViewModel.citiesData,::updateBackground)
        observeToast(todayForecastViewModel.showToast)
    }

    override fun initViewBinding() {
        binding = ActivityHomeBinding.inflate(layoutInflater)
        rootView = binding.root
        setContentView(rootView)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        changeStatusBarSetting(false)

        firstInit()
        App.prefManager.let { reqInfo(it.cityID,it.cityLat,it.cityLng) }

        binding.currentCityNameTv.setOnClickListener {

            cityPickerViewModel.cities.value?.let {
                cityPickerDialog(this, it, object: PickListener {
                    override fun onFinish(city: CityEntity) {
                        App.prefManager.let {pref ->
                            if (pref.cityID != city.cityId) {
                                reqInfo(city.cityId,city.lat,city.lng)
                                pref.cityID = city.cityId
                                pref.cityLat = city.lat
                                pref.cityLng = city.lng
                            }
                        }
                    }
                })
            }
        }

        binding.dimView.setOnClickListener {
            behavior.state = BottomSheetBehavior.STATE_COLLAPSED
            binding.dimView.visibility = View.GONE
        }

        behavior.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    BottomSheetBehavior.STATE_DRAGGING -> {}
                    BottomSheetBehavior.STATE_SETTLING -> {}
                    BottomSheetBehavior.STATE_EXPANDED -> binding.dimView.visibility = View.VISIBLE
                    BottomSheetBehavior.STATE_COLLAPSED -> binding.dimView.visibility = View.GONE
                    BottomSheetBehavior.STATE_HIDDEN -> {}
                    BottomSheetBehavior.STATE_HALF_EXPANDED -> {}
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                binding.dimView.alpha = slideOffset
                binding.bottomInfoInclude.bottomLin.alpha = slideOffset
            }
        })

        binding.mainSwipeRefresh.setOnRefreshListener {
            todayForecastViewModel.retryLoadingCurrentTempReq()
            lastWeekForecastViewModel.retryLoadingGeneralTempInfo()
            binding.mainSwipeRefresh.isRefreshing = false
        }
    }

    private fun firstInit() {
        binding.mainSwipeRefresh.setColorSchemeColors(ContextCompat.getColor(this,R.color.main_red))
        behavior = BottomSheetBehavior.from(binding.bottomSheetDesign)
        backgroundViewModel.registerTimeBroadcast()
//        updateBackground(backgroundViewModel.trans)
        bounce = AnimationUtils.loadLayoutAnimation(applicationContext, R.anim.layout_animation_fade_in)
        slideUp = AnimationUtils.loadLayoutAnimation(applicationContext, R.anim.layout_animation_slide_up)
    }

    private fun reqInfo(id:String,lat:String,lng:String) = App.prefManager.let {
        todayForecastViewModel.currentTempReq(id)
        lastWeekForecastViewModel.generalTempInfoReq(lat,lng)
    }

    private fun updateCurrentForecastInfo(currentForecastResponse: CurrentForecastResponse) {
        currentForecastResponse.let{
            binding.currentCityNameTv.text = it.name
            binding.currentTempDegTv.text = (it.main.temp.kelvinToCelsius().roundToInt().toString() +
                    getString(R.string.sgn_celsius))

            binding.minMaxDegTv.text = (it.main.minTemp.kelvinToCelsius().roundToInt().toString() +
                    getString(R.string.sgn_celsius) + "/" + it.main.minTemp.kelvinToCelsius().
            roundToInt().toString() + getString(R.string.sgn_celsius))

            binding.currentTempImg.setImageResource(DrawableUtils.getWeatherDrawableId(
                backgroundViewModel.currentTimeInt, it.weather[0].main))

            App.prefManager.cityName = it.name
        }

    }

    private fun updateGeneralForecastInfo(generalForecastResponse: GeneralForecastResponse) {
        generalForecastResponse.let{
            it.daily.removeAt(0)
            generalWeatherResponsesDailyList = it.daily
            generalWeatherResponsesHourlyList = ArrayList(it.hourly.subList(0, 24))

            if (todayForecastAdapter == null) initTodayWeatherRecyclerView()
            else todayForecastAdapter?.updateList(generalWeatherResponsesHourlyList)

            if (lastWeekForecastAdapter == null) initLastWeekWeatherRecyclerView()
            else lastWeekForecastAdapter?.updateList(generalWeatherResponsesDailyList)
        }

    }

    fun expandBottomView(position: Int, type: String, dayTitle: String, drawable: Drawable?) {
        when(type) {
            "week" -> {
                binding.bottomInfoInclude.let { bm ->

                    bm.tempLin.visibility = View.VISIBLE
                    bm.infoMainImg.setImageDrawable(drawable)

                    bm.infoDayTitleTv.text = dayTitle

                    generalWeatherResponsesDailyList.let { dailyList ->
                        bm.infoTempDegTv.text = (dailyList[position].temp.max.kelvinToCelsius()
                            .roundToInt().toString() + getString(R.string.sgn_celsius) + "/" + dailyList[position].temp.min.kelvinToCelsius()
                            .roundToInt() + getString(R.string.sgn_celsius))

                        bm.morningTempTv.text = (dailyList[position].temp.morn.kelvinToCelsius()
                            .roundToInt().toString() + getString(R.string.sgn_celsius))

                        bm.eveningTempTv.text = (dailyList[position].temp.eve.kelvinToCelsius()
                            .roundToInt().toString() + getString(R.string.sgn_celsius))

                        bm.nightTempTv.text = (dailyList[position].temp.night.kelvinToCelsius()
                            .roundToInt().toString() + getString(R.string.sgn_celsius))

                        bm.pressureTv.text = (dailyList[position].pressure.toString() +
                                getString(R.string.sgn_space) + getString(R.string.sgn_pascal))

                        bm.humidityTv.text = (dailyList[position].humidity.toString())

                        bm.dewPointTv.text = (dailyList[position].dewPoint.kelvinToCelsius()
                            .roundToInt().toString() + getString(R.string.sgn_celsius))

                        bm.windSpeedTv.text = (dailyList[position].windSpeed.toString() +
                                getString(R.string.sgn_space) + getString(R.string.sgn_speed_km_h))
                    }
                }
            }

            "day" -> {
                binding.bottomInfoInclude.let { bm ->

                    bm.tempLin.visibility = View.GONE
                    bm.infoMainImg.setImageDrawable(drawable)

                    bm.infoDayTitleTv.text = (getString(R.string.t_today) +
                            getString(R.string.sgn_space) + dayTitle)

                    generalWeatherResponsesHourlyList.let { hourlyList ->

                        bm.infoTempDegTv.text = (hourlyList[position].temp.kelvinToCelsius()
                            .roundToInt().toString() + getString(R.string.sgn_celsius))

                        bm.pressureTv.text = (hourlyList[position].pressure.toString() +
                                getString(R.string.sgn_space) + getString(R.string.sgn_pascal))

                        bm.humidityTv.text = (hourlyList[position].humidity.toString())

                        bm.dewPointTv.text = (hourlyList[position].dewPoint.kelvinToCelsius()
                            .roundToInt().toString() + getString(R.string.sgn_celsius))

                        bm.windSpeedTv.text = (hourlyList[position].windSpeed.toString() +
                                getString(R.string.sgn_space) + getString(R.string.sgn_speed_km_h))
                    }
                }
            }

        }

        binding.dimView.visibility = View.VISIBLE
        behavior.state = BottomSheetBehavior.STATE_EXPANDED
    }

    private fun initTodayWeatherRecyclerView() {
        binding.todayWeatherRecyclerView.let { recyclerView ->
            todayForecastAdapter = TodayForecastAdapter(this, generalWeatherResponsesHourlyList)
            recyclerView.layoutManager = LinearLayoutManager(this, GridLayoutManager.HORIZONTAL, false)
            recyclerView.setHasFixedSize(true)
            recyclerView.itemAnimator = DefaultItemAnimator()
            recyclerView.adapter = todayForecastAdapter
            recyclerView.isNestedScrollingEnabled = false
            recyclerView.layoutAnimation = bounce
            OverScrollDecoratorHelper.setUpOverScroll(recyclerView, OverScrollDecoratorHelper.ORIENTATION_HORIZONTAL)
        }

    }

    private fun initLastWeekWeatherRecyclerView() {
        binding.lastWeekWeatherRecyclerView.let { recyclerView ->
            lastWeekForecastAdapter = LastWeekForecastAdapter(this, generalWeatherResponsesDailyList)
            recyclerView.layoutManager = GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false)
            recyclerView.setHasFixedSize(true)
            recyclerView.itemAnimator = DefaultItemAnimator()
            recyclerView.adapter = lastWeekForecastAdapter
            recyclerView.isNestedScrollingEnabled = false
            recyclerView.layoutAnimation = slideUp
        }
    }

    private fun handleTodayForecastResponse(currentForecastResponse: NetworkResource<CurrentForecastResponse>) {
        swipeRefreshStat(true)
        when (currentForecastResponse) {
            is NetworkResource.NoConnection -> {
                currentForecastResponse.errorCode?.let { todayForecastViewModel.showToastMessage(it) }
                swipeRefreshStat(false)
            }

            is NetworkResource.Loading -> {
                showLoadingView()
                showDataView(false)
                swipeRefreshStat(false)
            }

            is NetworkResource.Success -> {
                currentForecastResponse.data?.let { updateCurrentForecastInfo(it)
                    showDataView(false)
                    swipeRefreshStat(false)
                }
            }

            is NetworkResource.DataError -> {
                showDataView(false)
                swipeRefreshStat(false)
            }
        }
    }

    private fun handleLastWeekForecastResponse(generalForecastResponse: NetworkResource<GeneralForecastResponse>) {
        when (generalForecastResponse) {
            is NetworkResource.NoConnection -> {
                swipeRefreshStat(false)
            }

            is NetworkResource.Loading -> {
                showLoadingView()
                showDataView(false)
                swipeRefreshStat(false)
            }

            is NetworkResource.Success -> {
                generalForecastResponse.data?.let {
                    updateGeneralForecastInfo(it)
                    showDataView(false)
                    swipeRefreshStat(false)
                }
            }

            is NetworkResource.DataError -> {
                showDataView(false)
                swipeRefreshStat(false)
            }
        }
    }

    @Suppress("SameParameterValue")
    private fun showDataView(show: Boolean) {
//        binding.currentCityNameTv.visibility = (show) then View.VISIBLE ?: View.INVISIBLE
        binding.currentCityNameTv.isClickable = !show

    }

    private fun swipeRefreshStat(stat: Boolean) {
//        binding.mainSwipeRefresh.isRefreshing = stat
        binding.mainSwipeRefresh.isEnabled = !stat
    }

    private fun showLoadingView() {
        EspressoIdlingResource.increment()
    }

    private fun updateBackground(trans: TransitionDrawable) {
        binding.mainScrollView.background = trans
    }

    private fun observeToast(event: LiveData<Event<Any>>) {
        binding.rootView.showToast(this, event, Toast.LENGTH_LONG)
    }

    public override fun onDestroy() {
        super.onDestroy()
        backgroundViewModel.unregisterTimeBroadcast()
    }

    override fun onBackPressed() {
        if (behavior.state == BottomSheetBehavior.STATE_EXPANDED) {
            behavior.setState(BottomSheetBehavior.STATE_COLLAPSED)
        } else {
            super.onBackPressed()
        }
    }
}