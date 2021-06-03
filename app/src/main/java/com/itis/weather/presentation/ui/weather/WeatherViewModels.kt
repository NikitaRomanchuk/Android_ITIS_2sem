package com.itis.weather.presentation.ui.weather

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.itis.weather.domain.common.ActionLiveData
import com.itis.weather.domain.common.UiState
import com.itis.weather.data.database.entity.WeatherDb
import com.itis.weather.data.repo.WeatherRepo
import kotlinx.coroutines.launch

class WeatherViewModels @ViewModelInject constructor(
    private val repo: WeatherRepo
) : ViewModel() {

    val weatherId = MutableLiveData<Int>()

    val allWeather: LiveData<List<WeatherDb>>
        get() = repo.getWeather()

    val findWeather: LiveData<WeatherDb> =
        Transformations.switchMap(weatherId, repo::findWeather)

    val weatherState = ActionLiveData<UiState>()
    val findweatherState = ActionLiveData<UiState>()


    fun getWeatherLatLong(lat: Double?, lon: Double?, key: String) {
        viewModelScope.launch {
            try {
                repo.refreshWeather(lat ?: 0.0, lon ?: 0.0, key)
            } catch (error: Exception) {
                weatherState.sendAction(UiState.Error(error.message.orEmpty()))
            }
        }
    }

    fun getWeatherLatLong(city: String, key: String) {
        viewModelScope.launch {
            try {
                repo.refreshWeatherByCity(city, key)
                findweatherState.sendAction(UiState.Success)
            } catch (error: Exception) {
                findweatherState.sendAction(UiState.Error(error.message.orEmpty()))
            }
        }
    }
}