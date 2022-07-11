package com.plcoding.weatherapp.domain.location

import android.location.Location

interface LocationTracker {

    //nullable in case the use hasn't enabled location/connection error
    suspend fun getCurrentLocation():Location?
}