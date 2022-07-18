package com.example.pokeapi.di

import com.example.pokeapi.data.datasource.remote.retrofit.OkHttpClientManager
import com.example.pokeapi.data.datasource.remote.retrofit.PokemonService
import com.example.pokeapi.data.datasource.remote.retrofit.RetrofitProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun providesOkHttpClient(): OkHttpClientManager {
        return OkHttpClientManager()
    }

    @Provides
    @Singleton
    fun providesRetrofitInstance(okHttpClient: OkHttpClientManager): RetrofitProvider {
        return RetrofitProvider(okHttpClient)
    }

    @Provides
    @Singleton
    fun providesPokemonService(retrofitProvider: RetrofitProvider): PokemonService {
        return retrofitProvider.retrofit.create(PokemonService::class.java)
    }

}