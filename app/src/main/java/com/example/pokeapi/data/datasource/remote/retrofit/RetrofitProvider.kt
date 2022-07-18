package com.example.pokeapi.data.datasource.remote.retrofit

import com.squareup.moshi.Moshi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

const val BASE_URL = "https://pokeapi.co/api/v2/"

class RetrofitProvider(okHttpClient: OkHttpClientManager) {

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient.providesOkHttpClient())
        .addConverterFactory(MoshiConverterFactory.create().asLenient())
        .build()
}