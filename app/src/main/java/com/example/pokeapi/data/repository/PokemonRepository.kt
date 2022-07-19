package com.example.pokeapi.data.repository

import android.util.Log
import com.example.pokeapi.data.datasource.local.datasource.LocalPokemonDataSource
import com.example.pokeapi.data.datasource.local.room.Pokemon
import com.example.pokeapi.data.datasource.remote.datasource.RemotePokemonDataSource
import com.example.pokeapi.data.datasource.remote.models.PokemonListResponse
import com.example.pokeapi.data.datasource.remote.models.PokemonResponse
import com.example.pokeapi.data.datasource.remote.utils.Result
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject

class PokemonRepository @Inject constructor(
    private val remotePokemonDataSource: RemotePokemonDataSource,
    private val localPokemonDataSource: LocalPokemonDataSource
) : PokemonRepositoryInterface {

    override suspend fun getAllPokemon(): List<Pokemon> {
        return localPokemonDataSource.getAllPokemon()
    }

    override suspend fun getPokemonList(): Result<PokemonListResponse> {
        return invoke { remotePokemonDataSource.getPokemonList() }
    }

    override suspend fun getPokemonByName(name: String): Result<PokemonResponse> {
        return invoke { remotePokemonDataSource.getPokemonByName(name) }
    }

    override suspend fun insertPokemon(pokemonList: List<Pokemon>) {
        localPokemonDataSource.insertPokemon(pokemonList)
    }

    private suspend fun <T>invoke(block: suspend () -> Response<T>): Result<T> {
        return try {
            val response = block()
            val isSuccessful = response.isSuccessful && response.body() != null
            if (isSuccessful) Result.Success(response.body()!!) else Result.Failed
        } catch (ex: Exception) {
            Log.d(PokemonRepository::class.simpleName, "ex: ${ex.message}")
            Result.Failed
        }
    }
}