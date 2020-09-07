package com.hofinity.weatherito.presentation.ui.component.home.adapters.todayForecastAdapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.hofinity.weatherito.R
import com.hofinity.weatherito.databinding.ItemTodayForecastBinding
import com.hofinity.weatherito.domain.entity.forecast.Hourly
import com.hofinity.weatherito.domain.utils.DrawableUtils
import com.hofinity.weatherito.domain.utils.extentions.kelvinToCelsius
import com.hofinity.weatherito.domain.utils.extentions.then
import com.hofinity.weatherito.presentation.ui.component.home.HomeActivity
import kotlin.math.roundToInt

/**
 *  @author Smooke
 */

class TodayForecastViewHolder(private val binding: ItemTodayForecastBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(context: Context,hourly: Hourly,dayHours: ArrayList<String>, position: Int) {

        binding.timeTv.text = (dayHours[position] == "0") then "24" ?: (dayHours[position] + "")

        binding.statusImg.setImageResource(DrawableUtils.getWeatherDrawableId(dayHours[position], hourly.weather[0].main))

        binding.degTv.text = (hourly.temp.kelvinToCelsius().roundToInt().toString() + context.getString(R.string.sgn_celsius))

        binding.rootView.setOnClickListener {
            (context as HomeActivity).expandBottomView(position, "day"
                , binding.timeTv.text.toString(), binding.statusImg.drawable)
        }
    }
}