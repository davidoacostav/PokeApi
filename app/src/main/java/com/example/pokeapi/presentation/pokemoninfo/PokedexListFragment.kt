package com.example.pokeapi.presentation.pokemoninfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokeapi.data.datasource.local.room.Pokemon
import com.example.pokeapi.databinding.FragmentPokedexListBinding
import com.example.pokeapi.presentation.pokemoninfo.adapter.PokemonAdapter
import com.example.pokeapi.presentation.pokemoninfo.adapter.SortType
import com.example.pokeapi.presentation.utils.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokedexListFragment : Fragment() {

    private var _binding: FragmentPokedexListBinding? = null
    private val binding: FragmentPokedexListBinding get() = _binding!!
    private val viewModel by activityViewModels<PokemonViewModel>()
    private val pokemonAdapter by lazy {
        PokemonAdapter(this::onPokemonSelected)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPokedexListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            pokemonSearchEt.editText?.addTextChangedListener {
                val input = it.toString()
                viewModel.filterPokemon(input)
            }

            nameButton.setOnClickListener { onSortTypeSelected(SortType.NAME) }
            numberButton.setOnClickListener { onSortTypeSelected(SortType.NUMBER) }
            typeButton.setOnClickListener { onSortTypeSelected(SortType.TYPE) }

            pokedexRv.apply {
                adapter = pokemonAdapter
                addItemDecoration(
                    DividerItemDecoration(
                        requireContext(),
                        LinearLayoutManager.VERTICAL
                    )
                )
            }
            viewModel.uiState.observe(viewLifecycleOwner) { state ->
                handleLoading(state.isLoading)
                handleError(state.errorMsg)
                pokemonAdapter.submitList(state.pokemonToDisplay)
            }
        }
    }

    private fun handleLoading(isLoading: Boolean) = with(binding) {
        progressBar.isVisible = isLoading
        group.isVisible = !isLoading
    }

    private fun handleError(errorMsg: String?) {
        errorMsg?.let { showToast(errorMsg) }
    }

    private fun onPokemonSelected(pokemon: Pokemon) {
        viewModel.onPokemonSelected(pokemon)
        findNavController().navigate(PokedexListFragmentDirections.fragmentPokedexListToFragmentPokemonDetails())
    }

    private fun onSortTypeSelected(sortType: SortType) {
        viewModel.onSortTypeSelected(sortType)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}