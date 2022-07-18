package com.example.pokeapi.di

import com.example.pokeapi.data.datasource.local.datasource.LocalPokemonDataSource
import com.example.pokeapi.data.datasource.local.room.PokemonDao
import com.example.pokeapi.data.datasource.remote.datasource.RemotePokemonDataSource
import com.example.pokeapi.data.datasource.remote.retrofit.PokemonService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun providesLocalPokemonDataSource(pokemonDao: PokemonDao): LocalPokemonDataSource {
        return LocalPokemonDataSource(pokemonDao)
    }

    @Provides
    @Singleton
    fun providesRemotePokemonDataSource(pokemonService: PokemonService): RemotePokemonDataSource {
        return RemotePokemonDataSource(pokemonService)
    }
}