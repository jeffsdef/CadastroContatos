package com.example.cadastrocontatos.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    val service: BrasilApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://brasilapi.com.br/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BrasilApiService::class.java)
    }
}