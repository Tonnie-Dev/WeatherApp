package com.plcoding.weatherapp.domain.weather

data class WeatherInfo(

    //represent day as int(0..6) with 0 being today
    val weatherDataPerDay: Map<Int, List<WeatherData>>,

    //represent the current day or hour
    val currentWeatherData: WeatheData?
)
