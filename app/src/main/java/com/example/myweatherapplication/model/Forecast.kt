package com.example.myweatherapplication.model

data class Forecast(
    val date: String,
    val maxTemp: String,
    val minTemp: String,
    val icon: String,
    val hour: List<Hour>,
)