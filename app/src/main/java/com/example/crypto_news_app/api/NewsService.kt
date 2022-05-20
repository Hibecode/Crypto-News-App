package com.example.crypto_news_app.api

import com.example.crypto_news_app.RetrofitResp.NewsResponse
import retrofit2.Response
import retrofit2.http.GET

interface NewsService {

    @GET("v2/everything?q=crypto")
    fun getBreakingNews(

    ) : Response<NewsResponse>
}