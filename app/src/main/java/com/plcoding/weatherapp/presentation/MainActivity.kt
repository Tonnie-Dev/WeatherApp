package com.plcoding.weatherapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import com.plcoding.weatherapp.presentation.overview_screen.WeatherViewModel
import com.plcoding.weatherapp.presentation.ui.theme.WeatherAppTheme

class MainActivity : ComponentActivity() {

    val viewModel: WeatherViewModel by viewModels()
    lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        permissionLauncher =
            registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
                viewModel.
            }
        setContent {
            WeatherAppTheme {

            }
        }
    }
}