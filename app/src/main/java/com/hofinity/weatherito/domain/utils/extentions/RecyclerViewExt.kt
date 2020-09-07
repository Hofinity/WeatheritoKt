package com.hofinity.weatherito.domain.utils.extentions

import androidx.recyclerview.widget.RecyclerView

fun<T> RecyclerView.Adapter<*>.clearItems(list: ArrayList<T>) {
    val size = list.size
    if (size > 0) {
        list.subList(0, size).clear()
        notifyItemRangeRemoved(0, size)
    }
}