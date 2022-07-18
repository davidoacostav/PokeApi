package com.example.pokeapi.data.datasource.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PokemonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemon(pokemon: Pokemon)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAllPokemon(pokemonList: List<Pokemon>)

    @Query("SELECT * FROM pokemon")
    suspend fun getAllPokemon(): List<Pokemon>
}