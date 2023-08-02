package com.example.pagingapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pagingapp.databinding.AdapterItemCharacterBinding
import com.example.pagingapp.model.Characters

class CharacterViewHolder(view: AdapterItemCharacterBinding) : RecyclerView.ViewHolder(view.root) {

    private val binding: AdapterItemCharacterBinding

    init {
        binding = view
    }

    fun bind(character: Characters?) {
        if (character != null) {
            binding.tvName.text = character.name
            binding.tvGender.text = character.gender
            if (character.image.isNotEmpty()) {
                Glide.with(binding.imageThumb).load(character.image).into(binding.imageThumb)
//                Picasso.get().load(character.image).into(itemView.imageThumb)
            }
        }
    }

    companion object {
        fun create(parent: ViewGroup): CharacterViewHolder {
//            val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_item_character, parent, false)
            val binding = AdapterItemCharacterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return CharacterViewHolder(binding)
        }
    }
}