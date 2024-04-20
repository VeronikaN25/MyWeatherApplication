package com.example.myweatherapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myweatherapplication.constant.DEFAULT_WEATHER_DESTINATION
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class WeatherState {
    object Loading:WeatherState()
    data class Success(val weatherData:ForecastResponse):WeatherState()
    data class Error(val message:String):WeatherState()
}

class WeatherViewModel:ViewModel(){
    private val _weatherState= MutableStateFlow<WeatherState>(WeatherState.Loading)
    val weatherState:StateFlow<WeatherState> = _weatherState

    fun getWeather(city: String = DEFAULT_WEATHER_DESTINATION){
        viewModelScope.launch {
            try{
                val response=WeatherApi.weatherService.getWeatherForecast(
                    apiKey ="8f4cd9458a6c470180f153157241604 ",
                    location =city,
                    numDays = 7,
                )
                _weatherState.value=WeatherState.Success(response)
            }catch (r:Exception){
                _weatherState.value=WeatherState.Error("Failed to fetch weather data.")

            }
        }
    }
}
