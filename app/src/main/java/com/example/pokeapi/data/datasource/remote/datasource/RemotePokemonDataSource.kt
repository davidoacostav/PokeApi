package com.example.pokeapi.data.datasource.remote.datasource

import com.example.pokeapi.data.datasource.remote.models.PokemonListResponse
import com.example.pokeapi.data.datasource.remote.models.PokemonResponse
import com.example.pokeapi.data.datasource.remote.retrofit.PokemonService
import retrofit2.Response
import javax.inject.Inject

class RemotePokemonDataSource @Inject constructor(private val pokemonService: PokemonService) {

    suspend fun getPokemonList(): Response<PokemonListResponse> {
        return pokemonService.getPokemonList()
    }

    suspend fun getPokemonByName(name: String): Response<PokemonResponse> {
        return pokemonService.getPokemonByName(name)
    }
}