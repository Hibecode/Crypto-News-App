package com.example.crypto_news_app.api

import com.example.crypto_news_app.models.NewsResponse
import com.example.crypto_news_app.utils.Constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("v2/everything")
    suspend fun getBreakingNews(
        @Query("q")
        searchCategory: String = "crypto",
        @Query("page")
        pageNumber: Int = 1,
        @Query("apiKey")
        apiKey: String = API_KEY
    ) : Response<NewsResponse>

    @GET("v2/everything")
    suspend fun searchForNews(
        @Query("page")
        pageNo: Int = 1,
        @Query("q")
        searchQuery: String,
        @Query("apiKey")
        apiKey: String = API_KEY
    ) : Response<NewsResponse>

}