package com.example.crypto_news_app.RetrofitResp

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)