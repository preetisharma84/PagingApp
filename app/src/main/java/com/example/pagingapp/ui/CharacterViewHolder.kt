package com.example.pagingapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pagingapp.R
import com.example.pagingapp.model.Characters
import kotlinx.android.synthetic.main.adapter_item_character.view.*

class CharacterViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(character: Characters?) {
        if (character != null) {
            itemView.tvName.text = character.name
            itemView.tvGender.text = character.gender
            if (character.image.isNotEmpty()) {
                Glide.with(itemView.imageThumb).load(character.image).into(itemView.imageThumb)
//                Picasso.get().load(character.image).into(itemView.imageThumb)
            }
        }
    }

    companion object {
        fun create(parent: ViewGroup): CharacterViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.adapter_item_character, parent, false)
            return CharacterViewHolder(view)
        }
    }
}