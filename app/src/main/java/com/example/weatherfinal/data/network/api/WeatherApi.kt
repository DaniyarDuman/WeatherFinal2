package com.example.weatherfinal.data.network.api

import com.example.weatherfinal.domain.CityWeatherResponse
import com.example.weatherfinal.domain.Temp
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("search.json")
    suspend fun searchCities(@Query("q") query: String): List<CityWeatherResponse>

    @GET("current.json")
    suspend fun current(@Query("q") query: String): Temp
}




