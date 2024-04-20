package com.example.myweatherapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue


class MainActivity : ComponentActivity() {
    private val weatherviewModel: WeatherViewModel by viewModels()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme{
               HomeScreen(weatherViewModel = weatherviewModel, uiState = WeatherUiState())
            }
        }
        weatherviewModel.getWeather(city = "Astana")
    }
}
@ExperimentalMaterial3Api
@Composable
fun HomeScreen(weatherViewModel: WeatherViewModel,uiState: WeatherUiState) {
    val weatherState by weatherViewModel.weatherState.collectAsState()
    WeatherScreen(weatherViewModel,uiState)
}





