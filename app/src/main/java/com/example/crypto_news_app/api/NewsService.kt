package com.example.crypto_news_app.api

import com.example.crypto_news_app.RetrofitResp.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET("v2/top-headlines")
    fun getBreakingNews(
        @Query("country")
        countryCode: String,
        @Query("q")
        search: String = "crypto",
        @Query("page")
        pageNumber: String,
        @Query("apiKey")
        apiKey: String = "rn"
    ) : Response<NewsResponse>


}