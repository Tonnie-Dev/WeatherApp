package com.plcoding.weatherapp.domain.weather

import com.plcoding.weatherapp.domain.model.WeatherData

data class WeatherInfo(

    //represent day as int(0..6) with 0 being today
    val weatherDataPerDay: Map<Int, List<WeatherData>>,

    //represent the current hour
    val currentWeatherData: WeatherData?
)
