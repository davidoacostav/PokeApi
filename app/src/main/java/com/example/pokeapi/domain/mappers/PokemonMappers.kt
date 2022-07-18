package com.example.pokeapi.domain.mappers

import com.example.pokeapi.data.datasource.local.room.Pokemon
import com.example.pokeapi.data.datasource.remote.models.PokemonResponse

fun PokemonResponse.toPokemon(): Pokemon {
    return Pokemon(
        number = id,
        name = name,
        types = types.map { it.type.name },
        height = height.toString(),
        weight = weight.toString(),
        image = sprites.frontDefault
    )
}