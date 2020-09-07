package com.hofinity.weatherito.presentation.ui.component.home.adapters.todayForecastAdapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hofinity.weatherito.databinding.ItemTodayForecastBinding
import com.hofinity.weatherito.domain.entity.forecast.Hourly
import com.hofinity.weatherito.domain.utils.TimeUtils
import java.util.ArrayList

/**
 * @author Smooke
 *
 *
 * */
class TodayForecastAdapter(var context: Context, var list: ArrayList<Hourly>)
    : RecyclerView.Adapter<TodayForecastViewHolder>() {

    private var dayHours: ArrayList<String> = TimeUtils().shiftedDayHours()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodayForecastViewHolder {
        val binding= ItemTodayForecastBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TodayForecastViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TodayForecastViewHolder, @SuppressLint("RecyclerView") position: Int) {
        val hourly = list[position]
        holder.bind(context,hourly, dayHours, position)
    }

    fun updateList(newList: ArrayList<Hourly>) {
        list.clear()
        list.addAll(newList)
        dayHours.clear()
        dayHours.addAll(TimeUtils().shiftedDayHours())
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