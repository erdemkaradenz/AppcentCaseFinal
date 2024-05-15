package com.example.appcentdeneme2.models

import com.example.appcentdeneme2.models.Article

data class NewsResponse(
    val articles: MutableList<Article>,
    val status: String,
    val totalResults: Int
)