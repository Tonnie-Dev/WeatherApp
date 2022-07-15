package com.plcoding.weatherapp.presentation

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.plcoding.weatherapp.presentation.overview_screen.WeatherCard
import com.plcoding.weatherapp.presentation.overview_screen.WeatherForecast
import com.plcoding.weatherapp.presentation.overview_screen.WeatherViewModel
import com.plcoding.weatherapp.presentation.ui.theme.DarkBlue
import com.plcoding.weatherapp.presentation.ui.theme.DeepBlue
import com.plcoding.weatherapp.presentation.ui.theme.WeatherAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: WeatherViewModel by viewModels()
    lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        permissionLauncher =
            registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
                viewModel.loadWeatherInfo()
            }

        permissionLauncher.launch(
                arrayOf(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                )
        )

        setContent {
            WeatherAppTheme {

                Column(
                        modifier = Modifier
                                .fillMaxSize()
                                .background(DeepBlue)
                ) {
                    WeatherCard(
                            state = viewModel.state,
                            background = DarkBlue,

                    )
                    
                    WeatherForecast(state = viewModel.state)
                }
            }
        }
    }
}