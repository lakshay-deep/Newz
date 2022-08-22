package com.lakshay.newsapp.ui.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lakshay.newsapp.data.model.News
import com.lakshay.newsapp.data.model.RestClientResult
import com.lakshay.newsapp.data.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {

    private val _newsLivedata = MutableLiveData<RestClientResult<News>>()
    val newsLiveData: LiveData<RestClientResult<News>>
    get() = _newsLivedata

    fun fetchNews(country: String, apiKey: String, pageSize: Int) {
        viewModelScope.launch{
            val result = newsRepository.fetchNews(country, apiKey, pageSize)
            _newsLivedata.postValue(result)
        }
    }
}