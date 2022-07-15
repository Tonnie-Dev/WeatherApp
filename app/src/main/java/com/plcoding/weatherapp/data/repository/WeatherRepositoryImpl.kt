package com.plcoding.weatherapp.data.repository

import com.plcoding.weatherapp.data.mapper.toWeatherInfo
import com.plcoding.weatherapp.data.remote.WeatherAPI
import com.plcoding.weatherapp.domain.repository.WeatherRepository
import com.plcoding.weatherapp.domain.util.Resource
import com.plcoding.weatherapp.domain.weather.WeatherInfo
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(private val api: WeatherAPI) : WeatherRepository {
    override suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo> {

        val result = try {
            Timber.i("Repo -Success")
Timber.i("Lat is: $lat, Long is: $long")

            Timber.i("The data is ${ api.getWeatherData(lat = lat, long = long)
                    .toWeatherInfo()}")
            Resource.Success(
                    data = api.getWeatherData(lat = lat, long = long)
                            .toWeatherInfo()
            )
        } catch (e: IOException) {
            Timber.i("Repo -IOException")
            Resource.Error(e.localizedMessage ?: "Please check your connection")
        } catch (e: HttpException) {

            Timber.i("Repo -HttPException")
            Resource.Error(e.localizedMessage ?: "Unknown Error Occurred")

        }

        return result
    }
    //moshi.JsonDataException: Required value  missing at $

}