package com.plcoding.weatherapp.presentation.overview_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.weatherapp.domain.location.LocationTracker
import com.plcoding.weatherapp.domain.repository.WeatherRepository
import com.plcoding.weatherapp.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository,
    private val locationTracker: LocationTracker
) : ViewModel() {

    var state by mutableStateOf(WeatherState())
        private set
/*
    init {
        loadWeatherInfo()
    }*/

 fun loadWeatherInfo() {
Timber.i("inside loadWeatherInfo fxn")
        state = state.copy(isLoading = true)
        viewModelScope.launch {
            Timber.i("inside launch")

            locationTracker.getCurrentLocation()
                    ?.let {

                        when (val result = repository.getWeatherData(it.latitude, it.longitude)) {

                            is Resource.Success -> {
                                Timber.i("lat is: ${it.latitude} long is: ${it.longitude}")
                                Timber.i("ViewModel - Result Success")
                                state = state.copy(isLoading = false, weatherInfo = result.data)
                            }
                            is Resource.Error -> {

                                Timber.i("ViewModel - Result Error")
                                state = state.copy(
                                        isLoading = false,
                                        error = result.message,
                                        weatherInfo = null
                                )

                            }

                        }
                    } ?: run {

                Timber.i("inside run {} Location is null")
                state = state.copy(

                        isLoading = false,
                        error = "Couldn't retrieve location. Make sure to grant permission and enable GPS"
                )

            }
        }

    }

}