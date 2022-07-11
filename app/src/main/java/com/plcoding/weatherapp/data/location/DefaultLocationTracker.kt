package com.plcoding.weatherapp.data.location

import android.app.Application
import android.location.Location
import com.google.android.gms.location.FusedLocationProviderClient
import com.plcoding.weatherapp.domain.location.LocationTracker

class DefaultLocationTracker(
    application: Application,
    fusedLocationProviderClient: FusedLocationProviderClient
) : LocationTracker {

    
    override suspend fun getCurrentLocation(): Location? {
        TODO("Not yet implemented")
    }
}