package com.plcoding.weatherapp.presentation.overview_screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun WeatherForecastItem(
    modifier: Modifier = Modifier,
    time: String,
    temp: Double,
    weatherIcon: ImageVector,
    color: Color = Color.White
) {

    Column(
            modifier = modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = time, color = color, fontSize = 20.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Icon(
                imageVector = weatherIcon,
                contentDescription = null,
                modifier = Modifier.size(30.dp),
                tint = color
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = temp.toString(), color = color, fontSize = 20.sp)

    }
}