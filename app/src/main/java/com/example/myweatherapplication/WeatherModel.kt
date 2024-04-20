package com.example.myweatherapplication

import com.example.myweatherapplication.constant.BASE_URL
import com.example.myweatherapplication.constant.NUMBER_OF_DAYS
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService{
    @GET("forecast.json")
    suspend fun getWeatherForecast(
        @Query("key") apiKey:String,
        @Query("q") location:String,
        @Query("days") numDays: Int = NUMBER_OF_DAYS,
    ):ForecastResponse
}

object WeatherApi{
    private val retrofit=Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val weatherService:WeatherApiService= retrofit.create(WeatherApiService::class.java)
}