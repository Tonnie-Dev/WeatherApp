package com.plcoding.weatherapp.data.repository

import com.plcoding.weatherapp.data.mapper.toWeatherInfo
import com.plcoding.weatherapp.data.remote.WeatherAPI
import com.plcoding.weatherapp.domain.repository.WeatherRepository
import com.plcoding.weatherapp.domain.util.Resource
import com.plcoding.weatherapp.domain.weather.WeatherInfo
import retrofit2.HttpException
import java.io.IOException

class WeatherRepositoryImpl (private val api: WeatherAPI):WeatherRepository{
    override suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo> {

        val result =    try {


            Resource.Success(data = api.getWeatherData(lat = lat, long = long).toWeatherInfo())
        }catch (e:IOException){

            Resource.Error(e.localizedMessage ?: "Please check your connection")
        }
        catch (e:HttpException){
            Resource.Error(e.localizedMessage ?: "Unknown Error Occurred")
        }

        return result
    }


}