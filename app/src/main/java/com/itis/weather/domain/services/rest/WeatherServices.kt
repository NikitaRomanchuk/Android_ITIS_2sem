package com.itis.weather.domain.services.rest

import com.itis.weather.domain.services.entity.WeatherData
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherServices {

    @GET("weather")
    suspend fun getWeatherLatLong(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("units") units: String = "metric",
        @Query("appid") appid: String
    ): WeatherData

    @GET("weather")
    suspend fun getWeatherCity(
       @Query("q") city: String,
        @Query("units") units: String = "metric",
        @Query("appid") appid: String
    ): WeatherData

}