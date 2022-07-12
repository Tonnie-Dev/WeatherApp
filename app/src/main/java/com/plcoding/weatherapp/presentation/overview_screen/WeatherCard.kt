package com.plcoding.weatherapp.presentation.overview_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import java.time.format.DateTimeFormatter

@Composable
fun WeatherCard(
    state: WeatherState,
    background: Color,
    modifier: Modifier = Modifier
) {

    //show the card only when current weather is not null

    state.weatherInfo?.currentWeatherData?.let { data ->

        Card(
                backgroundColor = background,
                shape = RoundedCornerShape(10.dp),
                modifier = modifier.padding(16.dp)
        ) {

            Column(
                    modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
            ) {


                Text(
                        text = "Today ${data.time.format(DateTimeFormatter.ISO_DATE_TIME)}",
                        color = Color.White,
                        modifier = Modifier.align()
                )
            }
        }
    }


}