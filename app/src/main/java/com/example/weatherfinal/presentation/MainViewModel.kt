package com.example.weatherfinal.presentation

import androidx.lifecycle.ViewModel
import com.example.weatherfinal.domain.CityWeatherResponse
import com.example.weatherfinal.domain.GetCitiesTemperatureUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import androidx.lifecycle.viewModelScope
import com.example.weatherfinal.domain.CityWeatherUI
import kotlinx.coroutines.launch


class MainViewModel(private val useCase: GetCitiesTemperatureUseCase): ViewModel(){

    private val _cityWeatherResponseList = MutableStateFlow<List<CityWeatherUI>>(emptyList())
    val cityWeatherResponseList: StateFlow<List<CityWeatherUI>> get() = _cityWeatherResponseList

    fun searchCities(query: String) {
        if (query.length < 2) return
        viewModelScope.launch {
           val results =  useCase.invoke(query)
            _cityWeatherResponseList.value = results
        }
    }
}