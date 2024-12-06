package com.example.weatherfinal.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherfinal.R
import com.example.weatherfinal.databinding.ItemCityWeatherBinding
import com.example.weatherfinal.domain.CityWeatherUI

class CityWeatherAdapter : ListAdapter<CityWeatherUI, CityWeatherAdapter.CityWeatherViewHolder>(CityWeatherDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityWeatherViewHolder {
        val binding = ItemCityWeatherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CityWeatherViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CityWeatherViewHolder, position: Int) {
        val cityWeather = getItem(position)  // Получаем элемент из списка
        holder.bind(cityWeather)
    }

    class CityWeatherViewHolder(private val binding: ItemCityWeatherBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(cityWeather: CityWeatherUI) {
            binding.cityName.text = cityWeather.name.toString()
            binding.temperature.text = "${cityWeather.temp}°C"
        }
    }

    class CityWeatherDiffCallback : DiffUtil.ItemCallback<CityWeatherUI>() {
        override fun areItemsTheSame(oldItem: CityWeatherUI, newItem: CityWeatherUI): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CityWeatherUI, newItem: CityWeatherUI): Boolean {
            return oldItem == newItem
        }
    }
}