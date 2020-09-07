package com.hofinity.weatherito.presentation.ui.component.cityPicker.adapters

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hofinity.weatherito.databinding.ItemCityBinding
import com.hofinity.weatherito.domain.entity.city.CityEntity
import com.hofinity.weatherito.presentation.ui.component.cityPicker.PickListener

class CityPickerAdapter(var context: Context, var list: ArrayList<CityEntity>, var pickListener: PickListener, var dialog: Dialog)
    : RecyclerView.Adapter<CityPickerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityPickerViewHolder {
        val binding = ItemCityBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CityPickerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CityPickerViewHolder, position: Int) {
        holder.bind(context,list[position],position,pickListener,dialog)
    }

    @Suppress("unused")
    fun updateList(newList: ArrayList<CityEntity>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }
}