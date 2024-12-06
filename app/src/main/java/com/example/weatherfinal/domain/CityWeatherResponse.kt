package com.example.weatherfinal.domain

data class CityWeatherResponse(
    val id: Long,
    val name: String,
)

data class CityWeatherUI(
    val id: Long,
    val name: String,
    val temp: Double
)

data class Temp(
    val temp_c: Double
)