package com.plcoding.weatherapp.presentation.overview_screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.format.DateTimeFormatter

@Composable
fun WeatherForecast(state: WeatherState, modifier: Modifier = Modifier) {

    //show column only when we have data available

    //pass key 0 to refer to today
    state.weatherInfo?.weatherDataPerDay?.get(1)
            ?.let { data ->

                Column(
                        modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp)
                ) {

                    Text(text = "Today", fontSize = 20.sp, color = Color.White)
                    Spacer(modifier = Modifier.height(16.dp))

                    LazyRow {

                        items(data) { item ->

                            WeatherForecastItem(
                                    time = item.time.format(
                                            DateTimeFormatter.ofPattern(
                                                    "HH:mm"
                                            )
                                    ),
                                    temp = item.temperatureCelsius,
                                    weatherIcon = ImageVector.vectorResource(id = item.weatherType.iconRes)
                            )


                        }

                    }

                }
            }
}