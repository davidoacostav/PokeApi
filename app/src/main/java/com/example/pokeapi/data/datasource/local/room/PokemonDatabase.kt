package com.example.pokeapi.data.datasource.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

const val DB_VERSION = 1
const val DB_NAME = "pokemon_database"

@Database(entities = [Pokemon::class], version = DB_VERSION, exportSchema = true)
@TypeConverters(Converters::class)
abstract class PokemonDatabase : RoomDatabase() {
    abstract fun getPokemonDao(): PokemonDao
}
