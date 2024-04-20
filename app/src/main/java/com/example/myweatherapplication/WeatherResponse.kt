package com.example.myweatherapplication

import com.example.myweatherapplication.model.Forecast
import com.example.myweatherapplication.model.Hour
import com.example.myweatherapplication.model.Weather


data class ForecastResponse(
    val location:Location,
    val current: Current,
    val forecast: NetworkForecast,
) {
    data class Current(
        val temp_c: Double,
        val last_updated: String,
        val condition: Condition
    ) {
        data class Condition(
            val text: String,
            val icon: String,
            val code: Int
        )
    }

    data class NetworkForecast(
        val forecastDay: List<ForecastDay>
    ) {
        data class ForecastDay(
            val date: String,
            val day: Day,
            val hour: List<NetworkHour>
        ) {
            data class Day(
                val maxtemp_c: Float,
                val mintemp_c: Float,
                val condition: Condition,
            ) {
                data class Condition(
                    val text: String,
                    val icon: String,
                    val code: Int
                )
            }
            data class NetworkHour(
                val time: String,
                val icon: String,
                val condition: Condition,
                val tempC: Double,
            ) {
                data class Condition(
                    val text: String,
                    val icon: String,
                    val code: Int
                )
            }
        }
    }

    data class Location(
        val name: String,
        val country: String,
        val region: String,
        val lat: Float,
        val lon: Float,
        val tz_id: String,
        val locationtime_epach: Long,
        val locationtime: Long,
    )
}

fun ForecastResponse.NetworkForecast.ForecastDay.NetworkHour.toHour(): Hour = Hour(
    time = time,
    icon = condition.icon,
    temperature = tempC.toInt().toString(),
)
fun ForecastResponse.NetworkForecast.ForecastDay.toWeatherForecast(): Forecast = Forecast(
    date = date,
    maxTemp = day.maxtemp_c.toInt().toString(),
    minTemp = day.mintemp_c.toInt().toString(),
    icon = day.condition.icon,
    hour = hour.map { networkHour ->
        networkHour.toHour()
    }
)
fun ForecastResponse.toWeather(): Weather = Weather(
    temperature = current.temp_c.toInt(),
    date = forecast.forecastDay[0].date,
    condition = current.condition,
    name = location.name,
    forecasts = forecast.forecastDay.map { networkForecastday ->
        networkForecastday.toWeatherForecast()
    }
)