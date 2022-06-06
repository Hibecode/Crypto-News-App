package com.example.crypto_news_app.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crypto_news_app.data.NewsRepository
import com.example.crypto_news_app.models.NewsResponse
import com.example.crypto_news_app.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class NewsViewModel(val repository: NewsRepository): ViewModel() {

    init {
        getBreakingNews( 1, "crypto")
    }

    val breakingNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var homePageNo = 1
    var searchCategory = "crypto"

    val searchNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var searchPageNo = 1


    fun getBreakingNews(pageNo: Int, searchCategory: String) = viewModelScope.launch {
        //breakingNews.postValue(Resource.Loading())
        val response = repository.getBreakingNews(searchCategory, pageNo)
        breakingNews.postValue(handlingBreakingNews(response))
    }

    fun getSearchNews(pageNo: Int, searchCategory: String) = viewModelScope.launch {
        //searchNews.postValue(Resource.Loading())
        val response = repository.searchForNews(searchPageNo, searchCategory)
        searchNews.postValue(handlingSearchNews(response))
    }

    private fun handlingBreakingNews(response: Response<NewsResponse>): Resource<NewsResponse> {
        if(response.isSuccessful){
            response.body()?.let { returnResult ->
                return Resource.Success(returnResult) }
            }
        return Resource.Error(response.message())
    }

    private fun handlingSearchNews(response: Response<NewsResponse>): Resource<NewsResponse> {
        if(response.isSuccessful){
            response.body()?.let { returnResult ->
                return Resource.Success(returnResult) }
        }
        return Resource.Error(response.message())
    }



}