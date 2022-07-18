package com.example.pokeapi.presentation.pokemoninfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.pokeapi.R
import com.example.pokeapi.databinding.FragmentPokemonDetailsBinding
import com.example.pokeapi.presentation.utils.loadImage
import com.example.pokeapi.presentation.utils.setTextOrDefault
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokemonDetailsFragment : Fragment() {

    private var _binding: FragmentPokemonDetailsBinding? = null
    private val binding: FragmentPokemonDetailsBinding get() = _binding!!
    private val viewModel by activityViewModels<PokemonViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPokemonDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            viewModel.uiState.observe(viewLifecycleOwner) { state ->
                val selectedPokemon = state.selectedPokemon ?: return@observe
                with(selectedPokemon) {
                    pokemonNameTv.setTextOrDefault(R.string.pokemon_name, name)
                    pokemonNumberTv.setTextOrDefault(R.string.pokemon_number, number.toString())
                    pokemonIv.loadImage(image)
                    pokemonTypesTv.setTextOrDefault(
                        id = R.string.pokemon_types,
                        text = types.joinToString(", ")
                    )
                    pokemonHeightTv.setTextOrDefault(R.string.pokemon_height, height)
                    pokemonWeightTv.setTextOrDefault(R.string.pokemon_weight, weight)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}