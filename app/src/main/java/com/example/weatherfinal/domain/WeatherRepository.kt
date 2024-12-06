package com.example.weatherfinal.domain

import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    suspend fun getCitiesWeather(query: String): Flow<List<CityWeatherResponse>>
    suspend fun getTemp(name: String): Temp
}
