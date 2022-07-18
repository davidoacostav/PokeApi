package com.example.pokeapi.data.datasource.remote.utils

sealed class Result<out T> {
    object Loading: Result<Nothing>()
    data class Success<T>(val data: T) : Result<T>()
    object Failed : Result<Nothing>()
}