package com.example.crypto_news_app.db

import androidx.room.Database
import com.example.crypto_news_app.models.Article


@Database(entities = [Article::class], version = 1)
abstract class ArticleDB {
}