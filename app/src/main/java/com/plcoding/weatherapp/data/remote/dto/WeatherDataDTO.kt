package com.plcoding.weatherapp.data.remote.dto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeatherDataDTO(
    @field:Json(name = "pressure_msl")
    val pressures: List<Double>,
    @field:Json(name = "relativehumidity_2m")
    val humidities: List<Double>,
    @field:Json(name = "temperature_2m")
    val temperatures: List<Double>,
    @field:Json(name = "time")
    val time: List<String>,
    @field:Json(name = "weathercode")
    val weatherCodes: List<Double>,
    @field:Json(name = "windspeed_10m")
    val windSpeeds: List<Double>
)