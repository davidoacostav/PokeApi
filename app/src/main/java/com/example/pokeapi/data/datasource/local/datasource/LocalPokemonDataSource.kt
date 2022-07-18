package com.example.pokeapi.data.datasource.local.datasource

import com.example.pokeapi.data.datasource.local.room.Pokemon
import com.example.pokeapi.data.datasource.local.room.PokemonDao
import javax.inject.Inject

class LocalPokemonDataSource @Inject constructor(private val pokemonDao: PokemonDao) {

    suspend fun insertPokemon(pokemonList: List<Pokemon>) {
        pokemonDao.insertAllPokemon(pokemonList)
    }

    suspend fun getAllPokemon(): List<Pokemon> {
        return pokemonDao.getAllPokemon()
    }
}