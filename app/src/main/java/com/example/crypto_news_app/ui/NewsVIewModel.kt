package com.example.crypto_news_app.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crypto_news_app.data.NewsRepository
import com.example.crypto_news_app.models.Article
import com.example.crypto_news_app.models.NewsResponse
import kotlinx.coroutines.launch
import retrofit2.Response

class NewsViewModel(val repository: NewsRepository): ViewModel() {

    var breakingNewsResponse: MutableLiveData<Response<NewsResponse>> = MutableLiveData()


    fun getBreakingNews() = viewModelScope.launch {
        val response = repository.getBreakingNews("us", "crypto", 1)
        breakingNewsResponse.value = response
    }

}