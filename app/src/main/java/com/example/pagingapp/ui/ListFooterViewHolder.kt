package com.example.pagingapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pagingapp.databinding.AdapterItemFooterBinding
import com.example.pagingapp.model.State

class ListFooterViewHolder(view: AdapterItemFooterBinding) : RecyclerView.ViewHolder(view.root) {

    private val binding: AdapterItemFooterBinding

    init {
        binding = view
    }

    fun bind(status: State?) {
        binding.progressBarFooter.visibility =
            if (status == State.LOADING) View.VISIBLE else View.INVISIBLE
        binding.tvErrorFooter.visibility =
            if (status == State.ERROR) View.VISIBLE else View.INVISIBLE
    }

    companion object {
        fun create(retry: () -> Unit, parent: ViewGroup): ListFooterViewHolder {
            /* val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_item_footer, parent, false)*/
            val binding =
                AdapterItemFooterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            binding.tvErrorFooter.setOnClickListener { retry() }
            return ListFooterViewHolder(binding)
        }
    }
}