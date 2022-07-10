package com.plcoding.weatherapp.data.dto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Hourly(
    @Json(name = "pressure_msl")
    val pressureMsl: List<Double>,
    @Json(name = "relativehumidity_2m")
    val relativehumidity2m: List<Double>,
    @Json(name = "temperature_2m")
    val temperature2m: List<Double>,
    @Json(name = "time")
    val time: List<String>,
    @Json(name = "weathercode")
    val weathercode: List<Double>,
    @Json(name = "windspeed_10m")
    val windspeed10m: List<Double>
)