package com.example.weatherfinal.app

import retrofit2.Retrofit

interface RetrofitProvider {
    fun retrofit(): Retrofit
}