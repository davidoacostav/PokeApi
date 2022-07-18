package com.example.pokeapi.data.datasource.remote.models

import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class PokemonListResponse(
    val count: Int,
    val results: List<PokemonInfo>
) {
    @JsonClass(generateAdapter = true)
    data class PokemonInfo(
        val name: String,
        val url: String
    )
}