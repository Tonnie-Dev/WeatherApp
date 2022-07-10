package com.plcoding.weatherapp.data.dto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeatherDTO(

   @field: Json(name = "hourly")
    val weatherData: WeatherDataDTO

)