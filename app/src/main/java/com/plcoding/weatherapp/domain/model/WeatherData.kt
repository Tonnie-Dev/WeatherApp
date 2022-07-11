package com.plcoding.weatherapp.domain.model

import com.plcoding.weatherapp.domain.weather.WeatherType
import java.time.LocalDateTime

data class WeatherData(
    val time: LocalDateTime,
    val temperatureCelsius: Double,
    val pressure: Double,
    val windSpeed: Double,
    val humidity: Double,
    //icon and weather description
    val weatherType: WeatherType
)
