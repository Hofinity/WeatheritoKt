package com.hofinity.weatherito.domain.entity.city

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "city_tbl")
data class CityEntity (
    //    @PrimaryKey(autoGenerate = true) private int id;
    @PrimaryKey var id:Int = 0,
    @ColumnInfo var cityId: String = "",
    @ColumnInfo var title: String = "",
    @ColumnInfo var lat: String = "",
    @ColumnInfo var lng: String = "",
    )