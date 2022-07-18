package com.example.pokeapi.di

import com.example.pokeapi.data.datasource.local.datasource.LocalPokemonDataSource
import com.example.pokeapi.data.datasource.remote.datasource.RemotePokemonDataSource
import com.example.pokeapi.data.repository.PokemonRepository
import com.example.pokeapi.data.repository.PokemonRepositoryInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun providesPokemonRepository(
        remotePokemonDataSource: RemotePokemonDataSource,
        localPokemonDataSource: LocalPokemonDataSource
    ): PokemonRepositoryInterface {
        return PokemonRepository(remotePokemonDataSource, localPokemonDataSource)
    }
}