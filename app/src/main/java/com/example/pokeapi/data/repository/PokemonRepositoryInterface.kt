package com.example.pokeapi.data.repository

import com.example.pokeapi.data.datasource.local.room.Pokemon
import com.example.pokeapi.data.datasource.remote.models.PokemonListResponse
import com.example.pokeapi.data.datasource.remote.models.PokemonResponse
import com.example.pokeapi.data.datasource.remote.utils.Result

interface PokemonRepositoryInterface {

    suspend fun getAllPokemon(): List<Pokemon>

    suspend fun getPokemonList(): Result<PokemonListResponse>

    suspend fun getPokemonByName(name: String): Result<PokemonResponse>

    suspend fun insertPokemon(pokemonList: List<Pokemon>)
}