package com.hofinity.weatherito.domain.app

import android.content.Context
import androidx.multidex.MultiDexApplication
import com.hofinity.weatherito.domain.app.preferences.PrefManager
import com.hofinity.weatherito.presentation.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

open class App : MultiDexApplication(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    companion object {
        lateinit var context: Context
        val prefManager: PrefManager by lazy {
            PrefManager(context)
        }
    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        initDagger()
        FontsOverride.setDefaultFont(applicationContext, "MONOSPACE", "fonts/alice_regular.ttf")
    }

    open fun initDagger() {
        DaggerAppComponent.builder().build().inject(this)
    }
}
