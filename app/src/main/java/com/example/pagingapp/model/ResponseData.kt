package com.example.pagingapp.model

import com.google.gson.annotations.SerializedName

data class ResponseData(
    @SerializedName("articles")
    val news: List<News>
)

data class News(
    val title: String,
    @SerializedName("urlToImage")
    val image: String?
)