package com.hofinity.weatherito.presentation.ui.component.home.adapters.lastWeekForecastAdapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hofinity.weatherito.databinding.ItemLastWeekForecastBinding
import com.hofinity.weatherito.domain.entity.forecast.Daily
import com.hofinity.weatherito.domain.utils.DateUtils
import java.util.ArrayList

class LastWeekForecastAdapter(var context: Context, var list: ArrayList<Daily>)
    : RecyclerView.Adapter<LastWeekForecastViewHolder>() {

    private var weekDays: ArrayList<String> = DateUtils().shiftedWeekDays()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LastWeekForecastViewHolder {
        val itemBinding = ItemLastWeekForecastBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LastWeekForecastViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: LastWeekForecastViewHolder, @SuppressLint("RecyclerView") position: Int) {
        val daily = list[position]
        holder.bind(context,daily,weekDays,position)
    }

    fun updateList(newList: ArrayList<Daily>) {
        list.clear()
        list.addAll(newList)
        weekDays.clear()
        weekDays.addAll(DateUtils().shiftedWeekDays())
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