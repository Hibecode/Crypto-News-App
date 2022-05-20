package com.example.crypto_news_app.api

import com.example.crypto_news_app.RetrofitResp.NewsResponse
import com.example.crypto_news_app.Utils.Constants.Companion.API_KEY
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET("v2/top-headlines")
    fun getBreakingNews(
        @Query("country")
        countryCode: String,
        @Query("q")
        searchCategory: String = "crypto",
        @Query("page")
        pageNumber: Int = 1,
        @Query("apiKey")
        apiKey: String = API_KEY
    ) : Response<NewsResponse>

    @GET("v2/everything")
    fun searchForNews(
        @Query("page")
        pageNo: Int = 1,
        @Query("q")
        searchQuery: String,
        @Query("apiKey")
        apiKey: String = API_KEY
    ) : Response<NewsResponse>


    companion object {
        private const val BASE_URL = "https://newsapi.org"

        private val retrofit by lazy {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)

            val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()

            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        }

        val create: NewsService by lazy {
            retrofit.create(NewsService::class.java)
        }
    }


}