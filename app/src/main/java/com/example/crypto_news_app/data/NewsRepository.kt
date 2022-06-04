package com.example.crypto_news_app.data

import com.example.crypto_news_app.api.RetrofitInstance
import com.example.crypto_news_app.db.ArticleDB
import com.example.crypto_news_app.models.Article

class NewsRepository(private val db: ArticleDB) {


    fun getAllArticles() = db.articleDao().getAllArticles()

    suspend fun upsertArticle(article: Article) = db.articleDao().upsert(article)

    suspend fun deleteArticle(article: Article) = db.articleDao().delete(article)

    suspend fun getBreakingNews(searchCategory: String, pageNumber: Int) =
        RetrofitInstance.api.getBreakingNews(searchCategory, pageNumber)

    suspend fun searchForNews(pageNumber: Int, searchCategory: String) =
        RetrofitInstance.api.searchForNews(pageNumber, searchCategory)

}