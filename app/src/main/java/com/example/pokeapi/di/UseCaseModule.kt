package com.example.pokeapi.di

import com.example.pokeapi.data.repository.PokemonRepository
import com.example.pokeapi.domain.usecase.GetPokemonUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
object UseCaseModule {

    @Provides
    fun providesGetPokemonUseCase(pokemonRepository: PokemonRepository): GetPokemonUseCase {
        return GetPokemonUseCase(pokemonRepository)
    }
}