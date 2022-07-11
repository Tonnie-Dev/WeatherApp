package com.plcoding.weatherapp.di

import com.plcoding.weatherapp.data.remote.WeatherAPI
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    //provide API
    @Provides
    @Singleton
    fun provideWeatherAPI(): WeatherAPI {

       /* val moshi = Moshi.Builder()
                .addLast(KotlinJsonAdapterFactory())
                .build()*/

        return Retrofit.Builder()
                .baseUrl(WeatherAPI.BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
                .create()
    }
}