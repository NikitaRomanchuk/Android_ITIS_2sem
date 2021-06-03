package com.itis.weather.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WeatherDb (
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val main: String,
    val name: String,
    val icon: String,
    val description: String,
    val feelsLike: Double,
    val humidity: Double,
    val pressure: Double,
    val date: Long,
    val temp: Double,
    val tempMax: Double,
    val tempMin: Double
)