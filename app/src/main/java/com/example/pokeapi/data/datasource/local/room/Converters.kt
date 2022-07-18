package com.example.pokeapi.data.datasource.local.room

import androidx.room.TypeConverter

class Converters {

    @TypeConverter
    fun fromString(value: String) : List<String> {
        return value.split(",")
    }

    @TypeConverter
    fun listToString(values: List<String>): String {
        return values.joinToString(",")
    }
}