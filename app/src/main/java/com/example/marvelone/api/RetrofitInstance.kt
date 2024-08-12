package com.example.marvel.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance{

    private const val BASE_URL = "https://www.superheroapi.com/"

    private fun getInstance() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val marvelApi : MarvelApi = getInstance().create(MarvelApi::class.java)
}
