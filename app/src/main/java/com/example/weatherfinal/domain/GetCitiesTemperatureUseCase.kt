package com.example.weatherfinal.domain

import kotlinx.coroutines.flow.map

class GetCitiesTemperatureUseCase(private val repository: WeatherRepository) {

    suspend operator fun invoke(query: String): List<CityWeatherUI> {

        val citiesList = repository.getCitiesWeather(query)

        val results = mutableListOf<CityWeatherUI>()

        citiesList.collect {
            it.forEach { response ->
                val temp = repository.getTemp(response.name)
                results.add(CityWeatherUI(response.id, response.name, temp.temp_c))
            }
        }
        return results
    }
}