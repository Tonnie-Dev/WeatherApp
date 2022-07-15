package com.plcoding.weatherapp.presentation.overview_screen

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.plcoding.weatherapp.R
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
                            .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
            ) {
                //centered explicitly to Alignment.End
                Text(
                        text = "Today ${data.time.format(DateTimeFormatter.ofPattern("HH:mm"))}",
                        color = Color.White,
                        modifier = Modifier.align(Alignment.End)
                )

                Spacer(modifier = Modifier.height(16.dp))

                //centered horizontally by default
                Image(
                        painter = painterResource(id = data.weatherType.iconRes),
                        contentDescription = data.weatherType.weatherDesc,
                        modifier = Modifier.size(200.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))

                //centered horizontally by default
                Text(text = "${data.temperatureCelsius}°C", fontSize = 50.sp, color = Color.White)

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                        text = data.weatherType.weatherDesc,
                        color = Color.White,
                        fontSize = 20.sp
                )

                Spacer(modifier = Modifier.height(32.dp))

                Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                ) {

                    WeatherDataDisplay(
                            value = data.pressure.toInt(),
                            unit = "hpa",
                            icon = ImageVector.vectorResource(
                                    id = R.drawable.ic_pressure
                            ),
                            textStyle = TextStyle(color = Color.White)
                    )

                    WeatherDataDisplay(
                            value = data.humidity.toInt(),
                            unit = "%",
                            icon = ImageVector.vectorResource(
                                    id = R.drawable.ic_drop
                            ),
                            textStyle = TextStyle(color = Color.White)
                    )


                    WeatherDataDisplay(
                            value = data.windSpeed.toInt(),
                            unit = "km/h",
                            icon = ImageVector.vectorResource(
                                    id = R.drawable.ic_wind
                            ), textStyle = TextStyle(color = Color.White)
                    )
                    /*  WeatherSmallIcon(
                              text = "${data.pressure} km/h",
                              iconRes = R.drawable.ic_pressure
                      )
                      WeatherSmallIcon(text = "${data.humidity} %", iconRes = R.drawable.ic_drop)
                      WeatherSmallIcon(text = "${data.windSpeed} km/h", iconRes = R.drawable.ic_wind)*/
                }
            }
        }
    }


}

@Composable
fun WeatherSmallIcon(text: String, @DrawableRes iconRes: Int) {

    Row {
        Icon(painter = painterResource(id = iconRes), contentDescription = text)
        Spacer(modifier = Modifier.width(2.dp))
        Text(text = text, color = Color.White)
    }

}