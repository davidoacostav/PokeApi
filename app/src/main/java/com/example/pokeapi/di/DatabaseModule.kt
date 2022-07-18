package com.example.pokeapi.di

import android.content.Context
import androidx.room.Room
import com.example.pokeapi.data.datasource.local.room.DB_NAME
import com.example.pokeapi.data.datasource.local.room.PokemonDao
import com.example.pokeapi.data.datasource.local.room.PokemonDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun providesPokemonDatabase(@ApplicationContext context: Context): PokemonDatabase {
        return Room.databaseBuilder(context, PokemonDatabase::class.java, DB_NAME).build()
    }

    @Provides
    fun providesPokemonDao(database: PokemonDatabase): PokemonDao {
        return database.getPokemonDao()
    }
}