package com.example.pokeapi.presentation.pokemoninfo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokeapi.data.datasource.local.room.Pokemon
import com.example.pokeapi.data.datasource.remote.utils.Result
import com.example.pokeapi.domain.usecase.GetPokemonUseCase
import com.example.pokeapi.presentation.pokemoninfo.adapter.SortType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(
    private val getPokemonUseCase: GetPokemonUseCase
) : ViewModel() {

    private val initState = PokemonUiState(
        allPokemon = emptyList(),
        pokemonToDisplay = emptyList(),
        isLoading = true,
    )
    private var _uiState: MutableLiveData<PokemonUiState> = MutableLiveData(initState)
    val uiState: LiveData<PokemonUiState> get() = _uiState

    init {
        getPokemon()
    }

    private fun getPokemon() {
        viewModelScope.launch {
            getPokemonUseCase.invoke().collect { result ->
                _uiState.value = when (result) {
                    Result.Loading -> {
                        uiState.value?.copy(isLoading = true)
                    }
                    is Result.Success -> {
                        PokemonUiState(allPokemon = result.data, pokemonToDisplay = result.data)
                    }
                    else -> {
                        initState
                    }
                }
            }
        }
    }

    fun filterPokemon(query: String) {
        val tempState = _uiState.value
        _uiState.value = if (query.isBlank()) {
            uiState.value?.copy(pokemonToDisplay = tempState?.allPokemon.orEmpty())
        } else {
            uiState.value?.copy(pokemonToDisplay = tempState?.allPokemon?.filter {
                it.name.contains(query)
            }.orEmpty())
        }
    }

    fun onPokemonSelected(pokemon: Pokemon) {
        _uiState.value = _uiState.value?.copy(selectedPokemon = pokemon) ?: return
    }

    fun onSortTypeSelected(sortType: SortType) {
        val tempState = uiState.value ?: return
        _uiState.value = if (sortType == tempState.sortType) {
            tempState.copy(pokemonToDisplay = tempState.allPokemon)
        } else {
            if (tempState.allPokemon.isEmpty()) return
            val sortedList = sortListByType(tempState.allPokemon as MutableList<Pokemon>, sortType)
            tempState.copy(pokemonToDisplay = sortedList)
        }
    }

    private fun sortListByType(
        pokemonList: MutableList<Pokemon>,
        sortType: SortType
    ): List<Pokemon> {
        return when (sortType) {
            SortType.NAME -> pokemonList.sortedBy { it.name }
            SortType.TYPE -> pokemonList.sortedBy { it.types.firstOrNull() }
            SortType.NUMBER -> pokemonList.sortedBy { it.number }
        }
    }

    data class PokemonUiState(
        val allPokemon: List<Pokemon>,
        val pokemonToDisplay: List<Pokemon>,
        val selectedPokemon: Pokemon? = null,
        val isLoading: Boolean = false,
        val errorMsg: String? = null,
        val sortType: SortType? = null
    )
}