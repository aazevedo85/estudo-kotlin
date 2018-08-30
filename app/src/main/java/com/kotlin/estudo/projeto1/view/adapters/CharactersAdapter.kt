package com.kotlin.estudo.projeto1

import android.databinding.DataBindingUtil
import android.support.v4.app.ActivityOptionsCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kotlin.estudo.projeto1.R
import com.kotlin.estudo.projeto1.databinding.ItemCharacterBinding

class CharactersAdapter(var characterResponse: Model.CharacterResponse) : RecyclerView.Adapter<CharactersAdapter.ItemCharacterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemCharacterViewHolder {
        val itemCharacterBinding = DataBindingUtil.inflate<ItemCharacterBinding>(LayoutInflater.from(parent.context), R.layout.item_character, parent, false)

        return ItemCharacterViewHolder(itemCharacterBinding)

    }

    override fun onBindViewHolder(holder: ItemCharacterViewHolder, position: Int) {
        holder.bindItemCharacter(characterResponse.data.results[position])
    }

    override fun getItemCount() = characterResponse.data.results.size

    class ItemCharacterViewHolder(val binding: ItemCharacterBinding) : RecyclerView.ViewHolder(binding.cardView) {
        fun bindItemCharacter(character: Model.Character) {
            var viewmodel = ViewModel.CharacterViewModel(itemView.context, character)
            binding.cardView.setOnClickListener({ viewmodel.openDetailActivity() })
            binding.viewmodel = viewmodel

        }

    }

}
