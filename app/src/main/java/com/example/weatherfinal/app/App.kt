package com.example.weatherfinal.app

import android.app.Application
import com.example.weatherfinal.data.network.interceptor.ApiKeyInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class App: Application(), RetrofitProvider
{
    private lateinit var  retrofit: Retrofit

    override fun onCreate() {
        super.onCreate()
        createRetrofit()
    }

    private fun createRetrofit() {
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(provideOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(ApiKeyInterceptor(API_KEY))
            .build()
    }

    override fun retrofit() = retrofit


    companion object {
        private const val BASE_URL = "https://api.weatherapi.com/v1/"
        private const val API_KEY = "d4d53c301a3740cda14131600240612"
    }
}