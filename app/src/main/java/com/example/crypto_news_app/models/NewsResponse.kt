package com.example.crypto_news_app.models

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)