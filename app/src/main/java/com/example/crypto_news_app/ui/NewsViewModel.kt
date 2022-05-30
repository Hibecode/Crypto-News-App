package com.example.crypto_news_app.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crypto_news_app.data.NewsRepository
import com.example.crypto_news_app.models.Article
import com.example.crypto_news_app.models.NewsResponse
import com.example.crypto_news_app.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response
import java.lang.Exception

class NewsViewModel(val repository: NewsRepository): ViewModel() {

    val breakingNewsResponse: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var homeNewsPageNo = 1
    var searchCategory = "crypto"


    fun getBreakingNews() = viewModelScope.launch {
        breakingNewsResponse.postValue(Resource.Loading())
        val response = repository.getBreakingNews("us", searchCategory, homeNewsPageNo)
        breakingNewsResponse.postValue(handlingBreakingNews(response))
    }

    private fun handlingBreakingNews(response: Response<NewsResponse>): Resource<NewsResponse> {
        if(response.isSuccessful){
            response.body()?.let { returnResult ->
                return Resource.Success(returnResult) }
            }
        return Resource.Error(response.message())
    }

}