package com.example.pagingapp.ui

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.pagingapp.model.Characters
import com.example.pagingapp.model.State

class CharacterListAdapter(private val retry: () -> Unit) :
    PagedListAdapter<Characters, RecyclerView.ViewHolder>(CharacterDiffCallback) {

    private val DATA_VIEW_TYPE = 1
    private val FOOTER_VIEW_TYPE = 2
    private var state = State.LOADING

    companion object {
        val CharacterDiffCallback = object : DiffUtil.ItemCallback<Characters>() {
            override fun areItemsTheSame(oldItem: Characters, newItem: Characters): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Characters, newItem: Characters): Boolean {
                return oldItem.name == newItem.name && oldItem.gender == newItem.gender && oldItem.image == oldItem.image
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == DATA_VIEW_TYPE) CharacterViewHolder.create(parent)
        else ListFooterViewHolder.create(retry, parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == DATA_VIEW_TYPE)
            (holder as CharacterViewHolder).bind(getItem(position))
        else (holder as ListFooterViewHolder).bind(state)
    }

    override fun getItemViewType(position: Int): Int {
        return if (position < super.getItemCount()) DATA_VIEW_TYPE
        else FOOTER_VIEW_TYPE
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + if (hasFooter()) 1
        else 0
    }

    private fun hasFooter(): Boolean {
        return super.getItemCount() != 0 && (state == State.LOADING || state == State.ERROR)
    }

    fun setState(state: State) {
        this.state = state
        notifyItemChanged(super.getItemCount())
    }
}