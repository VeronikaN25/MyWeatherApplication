package com.example.myweatherapplication.model


import com.example.myweatherapplication.ForecastResponse

data class Weather(
    val temperature:Int,
    val date:String,
    val condition: ForecastResponse.Current.Condition,
    val name:String,
    val forecasts: List<Forecast>
)
