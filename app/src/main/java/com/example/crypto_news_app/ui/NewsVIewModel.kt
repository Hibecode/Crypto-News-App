package com.example.crypto_news_app.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crypto_news_app.data.NewsRepository
import com.example.crypto_news_app.models.Article
import kotlinx.coroutines.launch
import retrofit2.Response

class NewsViewModel(repository: NewsRepository): ViewModel() {

    val newResponse: MutableLiveData<Response<Article>> = MutableLiveData()


    fun getBreakingNews() = viewModelScope.launch {

    }

}