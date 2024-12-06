package com.example.weatherfinal.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.weatherfinal.domain.GetCitiesTemperatureUseCase

class MyViewModelFactory(val useCase: GetCitiesTemperatureUseCase): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(useCase) as T
    }
}


