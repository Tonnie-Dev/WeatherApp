package com.plcoding.weatherapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class WeatherApp:Application() {

    override fun onCreate() {
        super.onCreate()
    }
}