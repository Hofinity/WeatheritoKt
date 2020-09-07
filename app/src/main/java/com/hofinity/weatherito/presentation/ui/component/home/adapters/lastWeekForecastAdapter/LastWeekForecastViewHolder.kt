package com.hofinity.weatherito.presentation.ui.component.home.adapters.lastWeekForecastAdapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.hofinity.weatherito.R
import com.hofinity.weatherito.databinding.ItemLastWeekForecastBinding
import com.hofinity.weatherito.domain.entity.forecast.Daily
import com.hofinity.weatherito.domain.utils.DrawableUtils
import com.hofinity.weatherito.domain.utils.extentions.kelvinToCelsius
import com.hofinity.weatherito.domain.utils.extentions.then
import com.hofinity.weatherito.presentation.ui.component.home.HomeActivity
import kotlin.math.roundToInt

/**
 *  @author Smooke
 */

class LastWeekForecastViewHolder(private val binding: ItemLastWeekForecastBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(context: Context,daily: Daily,weekDays: ArrayList<String>,position: Int) {

        binding.statusImg.setImageResource(DrawableUtils.getWeatherDrawableId(-1, daily.weather[0].main))

        binding.weekDayTv.text = (position == 0) then context.resources.getString(R.string.t_tomorrow) ?: weekDays[position]

        binding.minDegTv.text = (daily.temp.min.kelvinToCelsius().roundToInt().toString() + context.getString(R.string.sgn_celsius))

        binding.maxDegTv.text = (daily.temp.max.kelvinToCelsius().roundToInt().toString() + context.getString(R.string.sgn_celsius) + "/")


        binding.rootView.setOnClickListener {
            (context as HomeActivity).expandBottomView(position, "week",
                binding.weekDayTv.text.toString(), binding.statusImg.drawable)
        }
    }
}