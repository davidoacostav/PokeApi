package com.example.pokeapi.domain.usecase

import com.example.pokeapi.data.datasource.local.room.Pokemon
import com.example.pokeapi.data.datasource.remote.utils.Result
import com.example.pokeapi.data.repository.PokemonRepository
import com.example.pokeapi.domain.mappers.toPokemon
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetPokemonUseCase @Inject constructor(private val pokemonRepository: PokemonRepository) {

    operator fun invoke(): Flow<Result<List<Pokemon>>> {
        return fetchPokemon()
    }

    private fun fetchPokemon() = flow {
        emit(Result.Loading)
        val pokemonInfoList = pokemonRepository.getPokemonList()
        if (pokemonInfoList is Result.Success) {
            val pokemonList = mutableListOf<Pokemon>()
            pokemonInfoList.data.results.forEach() { pokemonInfo ->
                val pokemon = pokemonRepository.getPokemonByName(pokemonInfo.name)
                if (pokemon is Result.Success) {
                    pokemonList.add(pokemon.data.toPokemon())
                }
            }
            pokemonRepository.insertPokemon(pokemonList)
        }
        emit(Result.Success(pokemonRepository.getAllPokemon()))
    }.flowOn(Dispatchers.IO)
}