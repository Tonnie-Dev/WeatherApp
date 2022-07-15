package com.plcoding.weatherapp.presentation.overview_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun WeatherForecastItem(
    modifier: Modifier = Modifier,
    time: String,
    temp: Double,
    weatherIcon: ImageVector,
    textColor: Color = Color.White
) {

    Column(modifier = modifier) {
        Text(text = time, color = textColor, fontSize = 20.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Icon(imageVector = weatherIcon, contentDescription = null)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = temp.toString(), color = textColor, fontSize = 20.sp)

    }
}