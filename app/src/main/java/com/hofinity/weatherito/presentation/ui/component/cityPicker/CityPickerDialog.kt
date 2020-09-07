package com.hofinity.weatherito.presentation.ui.component.cityPicker

import android.app.Activity
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import android.widget.ImageView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hofinity.weatherito.R
import com.hofinity.weatherito.domain.entity.city.CityEntity
import com.hofinity.weatherito.presentation.ui.component.cityPicker.adapters.CityPickerAdapter

object CityPickerDialog : LifecycleOwner {

    private lateinit var dialog: Dialog

    fun cityPickerDialog(activity: Activity, cityList: ArrayList<CityEntity>, pickListener: PickListener) {
        dialog = Dialog(activity)
        dialog.let {
            it.requestWindowFeature(Window.FEATURE_NO_TITLE)
            it.setCancelable(true)
            it.setContentView(R.layout.dialog_city_picker)
        }

        dialog.window?.let {
            it.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            it.attributes.windowAnimations = R.style.DialogFadeAnimation
        }

        val dismissImg = dialog.findViewById<ImageView>(R.id.dismissImg)
        val cityRecyclerView: RecyclerView = dialog.findViewById(R.id.cityRecyclerView)

        val cityPickerAdapter = CityPickerAdapter(activity, cityList, pickListener, dialog)

        cityRecyclerView.let {
            it.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            it.setHasFixedSize(true)
            it.itemAnimator = DefaultItemAnimator()
            it.adapter = cityPickerAdapter
            it.isNestedScrollingEnabled = false
        }

        dismissImg.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }


    @Suppress("unused")
    fun dismissCityPickerDialog() = dialog.dismiss()

    override fun getLifecycle(): Lifecycle {
        TODO("Not yet implemented")
    }
}