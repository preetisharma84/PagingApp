package com.example.pagingapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pagingapp.R
import com.example.pagingapp.model.News
import kotlinx.android.synthetic.main.adapter_item_articles.view.*

class NewsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(news: News?) {
        if (news != null) {
            itemView.tvNewsName.text = news.title
            if (!news.image.isNullOrEmpty()) {
                Glide.with(itemView.ivNewsBanner).load(news.image).into(itemView.ivNewsBanner)
            }
        }
    }

    companion object {
        fun create(parent: ViewGroup): NewsViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.adapter_item_articles, parent, false)
            return NewsViewHolder(view)
        }
    }
}