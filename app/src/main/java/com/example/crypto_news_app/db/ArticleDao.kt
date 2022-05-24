package com.example.crypto_news_app.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.crypto_news_app.models.Article


@Dao
interface ArticleDao {

    @Query("SELECT * FROM articles")
    fun getAllArticles(): LiveData<List<Article>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(article: Article): Long

    @Delete
    suspend fun delete(article: Article)


}