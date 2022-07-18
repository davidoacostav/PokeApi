package com.example.pokeapi.presentation.pokemoninfo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pokeapi.R
import com.example.pokeapi.data.datasource.local.room.Pokemon
import com.example.pokeapi.databinding.PokedexRowItemBinding
import com.example.pokeapi.presentation.utils.loadImage
import com.example.pokeapi.presentation.utils.setTextOrDefault

class PokemonAdapter(
    private val onClick: (Pokemon) -> Unit
) : ListAdapter<Pokemon, PokemonAdapter.PokemonViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        return PokemonViewHolder(
            PokedexRowItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        ).also { viewHolder ->
            viewHolder.itemView.setOnClickListener {
                onClick(currentList[viewHolder.adapterPosition])
            }
        }
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    override fun getItemCount(): Int {
        return currentList.size
    }


    class PokemonViewHolder(private val binding: PokedexRowItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(pokemon: Pokemon) = with(binding) {
            pokemonIv.loadImage(pokemon.image)
            pokemonNameTv.setTextOrDefault(R.string.pokemon_name, pokemon.name)
            pokemonNumberTv.setTextOrDefault(R.string.pokemon_number, pokemon.number.toString())
        }
    }
}

private class DiffCallback : DiffUtil.ItemCallback<Pokemon>() {

    override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon) =
        oldItem.number == newItem.number

    override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon) =
        oldItem == newItem
}