package com.itis.weather.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.itis.weather.data.database.entity.WeatherDb

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWeatherData(obj: WeatherDb)

    @Query("SELECT * FROM WeatherDb ORDER BY date DESC")
    fun getWeather(): LiveData<List<WeatherDb>>

    @Query("SELECT * FROM WeatherDb WHERE id = :id")
    fun findWeather(id: Int): LiveData<WeatherDb>


}