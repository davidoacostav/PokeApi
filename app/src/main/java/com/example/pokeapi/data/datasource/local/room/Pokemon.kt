package com.example.pokeapi.data.datasource.local.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon")
data class Pokemon(
    @PrimaryKey
    val number: Int,
    val name: String,
    val types: List<String>,
    val height: String,
    val weight: String,
    val image: String
)
