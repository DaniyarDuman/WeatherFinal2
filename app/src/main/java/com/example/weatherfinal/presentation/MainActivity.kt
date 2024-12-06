package com.example.weatherfinal.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherfinal.domain.GetCitiesTemperatureUseCase
import com.example.weatherfinal.app.RetrofitProvider
import com.example.weatherfinal.data.repository.WeatherRepositoryImpl
import com.example.weatherfinal.data.network.api.WeatherApi
import com.example.weatherfinal.databinding.ActivityMainBinding
import com.example.weatherfinal.domain.WeatherRepository
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var weatherAdapter: CityWeatherAdapter

    private var searchJob: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        createViewModel()
        setAdapter()
        setListeners()
        observeViewModel()
    }

    private fun observeViewModel() {
        lifecycleScope.launchWhenStarted {                            // repeatOnLifeCycle replace
            viewModel.cityWeatherResponseList.collect { cityWeatherList ->
                if (cityWeatherList.isEmpty()) {
                    Toast.makeText(this@MainActivity, "No cities found", Toast.LENGTH_SHORT).show()
                } else {
                    weatherAdapter.submitList(cityWeatherList)
                }
            }
        }
    }

    private fun setListeners() {
        binding.inputField.addTextChangedListener { editable ->
            val query = editable.toString()
            if (query.length >= 2) {
                searchJob?.cancel()
                searchJob = lifecycleScope.launch {
                    delay(300)
                    viewModel.searchCities(query)
                }
            }
        }
    }

    private fun createViewModel() {
        val retrofit = (application as RetrofitProvider).retrofit()
        val api = retrofit.create(WeatherApi::class.java)
        val repository: WeatherRepository = WeatherRepositoryImpl(api)
        val useCase = GetCitiesTemperatureUseCase(repository)
        val factory = MyViewModelFactory(useCase)
        viewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]
    }

    private fun setAdapter() {
        weatherAdapter = CityWeatherAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = weatherAdapter
    }
}