package com.example.pagingapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pagingapp.databinding.AdapterItemArticlesBinding
import com.example.pagingapp.model.News

class NewsViewHolder(view: AdapterItemArticlesBinding) : RecyclerView.ViewHolder(view.root) {

    private val binding: AdapterItemArticlesBinding

    init {
        binding = view
    }

    fun bind(news: News?) {
        if (news != null) {
            binding.tvNewsName.text = news.title
            if (!news.image.isNullOrEmpty()) Glide.with(binding.ivNewsBanner).load(news.image)
                .into(binding.ivNewsBanner)
        }
    }

    companion object {
        fun create(parent: ViewGroup): NewsViewHolder {
//            val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_item_articles, parent, false)
            val binding = AdapterItemArticlesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return NewsViewHolder(binding)
        }
    }
}