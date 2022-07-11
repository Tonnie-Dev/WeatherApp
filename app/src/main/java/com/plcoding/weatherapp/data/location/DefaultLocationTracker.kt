package com.plcoding.weatherapp.data.location

import android.Manifest
import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.plcoding.weatherapp.domain.location.LocationTracker
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume

class DefaultLocationTracker @Inject constructor(
    val application: Application,
    val fusedLocationProviderClient: FusedLocationProviderClient
) : LocationTracker {


    override suspend fun getCurrentLocation(): Location? {
        val hasAccessToCoarseLocationPermission =
            ContextCompat.checkSelfPermission(
                    application,
                    Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED

        val hasAccessToFineLocationPermission = ContextCompat.checkSelfPermission(
                application,
                Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        val locationManager =
            application.getSystemService(Context.LOCATION_SERVICE) as LocationManager

        val isGPSEnabled =
            locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER) || locationManager.isProviderEnabled(
                    LocationManager.GPS_PROVIDER
            )


        //return null permissions are denied or the providers are deactivate

        if (!hasAccessToCoarseLocationPermission || !hasAccessToFineLocationPermission || !isGPSEnabled) {
            return null
        }

        //if we have the permissions and the provider we then need to retrieve
        //the location in a suspending way

        return  suspendCancellableCoroutine {

            cont ->

            //returns a task which can use to listen for success or error cases
            fusedLocationProviderClient.lastLocation.apply {

                //if task is already complete i.e. there is already
                //a last known location

                if(this.isComplete){

                    //also check if task is successful as being complete doesn't
                    //mean the task was successful

                    if(this.isSuccessful){

                        //we resume coroutine with the provided location result
                        cont.resume(result)
                    }
                    //else takes care of failure
                    else{

                        //we resume coroutine with null location
                        cont.resume(null)
                    }
                    return@suspendCancellableCoroutine
                }

                //if the task was not complete we wait until it is completed

                //Async stuff starts here

                //this call back gives us the location
                addOnSuccessListener {

                    //we continue with the provided location
                    cont.resume(it)
                }

                addOnFailureListener{

                    // continue with a null location
                    cont.resume(null)
                }

                addOnCanceledListener {

                    //we cancel the coroutine
                    cont.cancel()
                }
            }
        }
    }


}