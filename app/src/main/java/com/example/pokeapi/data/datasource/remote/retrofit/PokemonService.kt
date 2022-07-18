package com.example.pokeapi.data.datasource.remote.retrofit

import com.example.pokeapi.data.datasource.remote.models.PokemonListResponse
import com.example.pokeapi.data.datasource.remote.models.PokemonResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonService {

    @GET("pokemon")
    suspend fun getPokemonList(@Query("limit") limit: Int = 151): Response<PokemonListResponse>

    @GET("pokemon/{name}")
    suspend fun getPokemonByName(@Path("name") name: String): Response<PokemonResponse>

}