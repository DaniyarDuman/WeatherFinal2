package com.example.weatherfinal.data.repository

import com.example.weatherfinal.data.network.api.WeatherApi
import com.example.weatherfinal.domain.CityWeatherResponse
import com.example.weatherfinal.domain.Temp
import com.example.weatherfinal.domain.WeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class WeatherRepositoryImpl(
    private val apiService: WeatherApi
): WeatherRepository {

    private val apiKey = "4cd80af0c77740d3bfe54507231708"

   override suspend fun getCitiesWeather(query: String): Flow<List<CityWeatherResponse>> = flow {
       val response = apiService.searchCities(query)
       emit(convertToDomainModel(response))
   }

    override suspend fun getTemp(name: String): Temp {
        return apiService.current(name)
    }

    private fun convertToDomainModel(apiModels: List<CityWeatherResponse>): List<CityWeatherResponse> {
        return apiModels.map {
            CityWeatherResponse(
                id = it.id,
                name = it.name,
            )
        }
    }
}
