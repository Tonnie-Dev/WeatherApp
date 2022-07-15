package com.plcoding.weatherapp.data.remote

import com.plcoding.weatherapp.data.remote.dto.WeatherDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {


  @GET("v1/forecast?hourly=temperature_2m,weathercode,relativehumidity_2m,windspeed_10m,pressure_msl")
    suspend fun getWeatherData(
        @Query("latitude") lat:Double,
        @Query("longitude") long:Double

    ): WeatherDTO

    companion object {

        const val BASE_URL = "https://api.open-meteo.com/"

    }
}

//https://api.open-meteo.com/v1/forecast?hourly=temperature_2m,weathercode,relativehumidity_2m,windspeed_10m,pressure_msl&latitude=42&longitude=36