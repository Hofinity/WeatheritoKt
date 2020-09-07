package com.hofinity.weatherito.presentation.ui.component.cityPicker.adapters

import android.app.Dialog
import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.hofinity.weatherito.databinding.ItemCityBinding
import com.hofinity.weatherito.domain.entity.city.CityEntity
import com.hofinity.weatherito.presentation.ui.component.cityPicker.PickListener

/**
 *  @author Smooke
 */

class CityPickerViewHolder(private val binding: ItemCityBinding) : RecyclerView.ViewHolder(binding.root) {
    @Suppress("UNUSED_PARAMETER")
    fun bind(context: Context, city: CityEntity, position: Int, pickListener: PickListener, dialog: Dialog) {

        binding.titleTv.text = city.title

        binding.rootView.setOnClickListener {
            pickListener.onFinish(city)
            dialog.dismiss()
        }
    }
}