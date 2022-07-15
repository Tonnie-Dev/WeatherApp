package com.plcoding.weatherapp.presentation

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
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

                Box(modifier = Modifier.fillMaxSize()) {


                    Column(
                            modifier = Modifier
                                    .fillMaxSize()
                                    .background(DeepBlue)
                    ) {
                        WeatherCard(
                                state = viewModel.state,
                                background = DarkBlue,

                                )
                        Spacer(modifier = Modifier.height(16.dp))
                        WeatherForecast(state = viewModel.state)
                    }

                    if (viewModel.state.isLoading) {

                        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))

                    }

                    //if error is non - null

                    viewModel.state.error?.let { error ->

                        Text(
                                text = error,
                                textAlign = TextAlign.Center,
                                color = Color.Red,
                                modifier = Modifier.align(
                                        Alignment.Center
                                )
                        )
                    }
                }


            }
        }
    }
}