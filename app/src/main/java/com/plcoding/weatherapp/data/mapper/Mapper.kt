package com.plcoding.weatherapp.data.mapper

import com.plcoding.weatherapp.data.remote.dto.WeatherDTO
import com.plcoding.weatherapp.data.remote.dto.WeatherDataDTO
import com.plcoding.weatherapp.domain.model.WeatherData
import com.plcoding.weatherapp.domain.weather.WeatherInfo
import com.plcoding.weatherapp.domain.weather.WeatherType
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


private data class IndexedWeatherData(val index: Int, val data: WeatherData)

fun WeatherDataDTO.toWeatherDataMap(): Map<Int, List<WeatherData>> {


    //take the top JsonArray with 24 x 7 items and iterate on it
    return this.times.mapIndexed { i, time ->

        //capture data at single point during iteration
        val temperature = temperatures[i]
        val pressure = pressures[i]
        val windSpeed = windSpeeds[i]
        val humidity = humidities[i]
        val weatherCode = weatherCodes[i]

        //store captured data at a single instant in a data class
        IndexedWeatherData(

                //i = 24 x 7 = 168
                index = i,

                data = WeatherData(
                        time = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME),
                        temperatureCelsius = temperature,
                        pressure = pressure,
                        windSpeed = windSpeed,
                        humidity = humidity,
                        weatherType = WeatherType.fromWMO(weatherCode)
                )
        )

        //groupBy returns a map of [index to WeatherData]
    }
            .groupBy {

                //truncate index by dividing it by 24 hours resulting to 0-6

                //at this point we have a map of [0-6day to List<IndexedWeatherData>
                it.index / 24


            }
            //apply map transformation to map itself i.e. Map the value which is List<IndexedWeatherData>
            .mapValues {

                //we then map the List<IndexedWeatherData>
                it.value.map { indexedWeatherData -> indexedWeatherData.data }

            }

}

fun WeatherDTO.toWeatherInfo():WeatherInfo{

    val weatherDataMap = this.weatherData.toWeatherDataMap()
    val now = LocalDateTime.now()

    //[0] is the current day
    val currentWeatherData = weatherDataMap[0]?.find {

        val hour = if(now.minute < 30) now.hour else now.hour + 1
        //compare currentWeatherData hour to the above hour variable
        //and return its weatherDataMap
        it.time.hour ==hour
    }

    return WeatherInfo(weatherDataPerDay = weatherDataMap, currentWeatherData = currentWeatherData)
}