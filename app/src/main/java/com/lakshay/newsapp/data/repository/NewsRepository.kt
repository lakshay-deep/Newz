package com.lakshay.newsapp.data.repository

import com.lakshay.newsapp.data.model.News
import com.lakshay.newsapp.data.model.RestClientResult

interface NewsRepository {

    suspend fun fetchNews(country: String, apiKey: String, pageSize: Int): RestClientResult<News>
}