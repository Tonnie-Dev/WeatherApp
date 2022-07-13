package com.plcoding.weatherapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.plcoding.weatherapp.presentation.overview_screen.WeatherViewModel
import com.plcoding.weatherapp.presentation.ui.theme.WeatherAppTheme

class MainActivity : ComponentActivity() {

    val viewModel:WeatherViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherAppTheme {
                
            }
        }
    }
}