package com.hofinity.weatherito.presentation.ui.component.home.viewModels

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.drawable.TransitionDrawable
import androidx.annotation.VisibleForTesting
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hofinity.weatherito.data.error.mapper.ErrorMapper
import com.hofinity.weatherito.domain.app.App
import com.hofinity.weatherito.domain.app.App.Companion.prefManager
import com.hofinity.weatherito.domain.usecase.errors.ErrorManager
import com.hofinity.weatherito.domain.utils.DrawableUtils
import com.hofinity.weatherito.presentation.ui.base.BaseViewModel
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class BackgroundViewModel  @Inject constructor() : BaseViewModel() {

    override val errorManager: ErrorManager
        get() = ErrorManager(ErrorMapper())

    lateinit var timeBroadcast: BroadcastReceiver

    private var currentTime: String
    var currentTimeInt: Int
    var backgroundColor: Int
    var trans: TransitionDrawable

    @VisibleForTesting
    val mBackgroundDrawable = MutableLiveData<TransitionDrawable>()
    val backgroundDrawable: LiveData<TransitionDrawable> get() = mBackgroundDrawable

    init {
        currentTime = SimpleDateFormat("HH", Locale.getDefault()).format(Date())
        currentTimeInt = currentTime.toInt()
        backgroundColor = DrawableUtils.timeDrawable(currentTimeInt)
        trans = ResourcesCompat.getDrawable(App.context.resources, backgroundColor, null) as TransitionDrawable
    }

    fun getBackground(backgroundColor: Int) {
        trans = ResourcesCompat.getDrawable(App.context.resources, backgroundColor, null) as TransitionDrawable
    }

    fun startBackgroundTrans(duration: Int) = trans.startTransition(duration)

    fun getCurrentTime() {
        currentTime = SimpleDateFormat("HH", Locale.getDefault()).format(Date())
        currentTimeInt = currentTime.toInt()
    }

    fun updatePrefManager() {
        prefManager.currentTimeDrawable = backgroundColor
    }

    private fun initTimeBroadcast() {
        timeBroadcast = object : BroadcastReceiver() {
            override fun onReceive(ctx: Context, intent: Intent) {
                if (Objects.requireNonNull(intent.action) == Intent.ACTION_TIME_TICK) {
                    getCurrentTime()
                    backgroundColor = DrawableUtils.timeDrawable(currentTimeInt)
                    getBackground(backgroundColor)
                    startBackgroundTrans(4000)
                    if (prefManager.currentTimeDrawable != backgroundColor) {
                        mBackgroundDrawable.value = trans
                        updatePrefManager()
                    }
                }
            }
        }
    }

    private fun firstInit() {
        getBackground(backgroundColor)
        startBackgroundTrans(100)
        mBackgroundDrawable.value = trans
        updatePrefManager()
    }

    fun registerTimeBroadcast() {
        firstInit()
        initTimeBroadcast()
        try {
            timeBroadcast.let {
                App.context.registerReceiver(it, IntentFilter(Intent.ACTION_TIME_TICK))
            }
        } catch (e: IllegalArgumentException) {
            e.printStackTrace()
        }
    }

    fun unregisterTimeBroadcast() {
        try {
            timeBroadcast.let { App.context.unregisterReceiver(it) }
        } catch (e: IllegalArgumentException) {
            e.printStackTrace()
        }
    }

}