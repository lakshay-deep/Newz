package com.lakshay.newsapp.data.network

import com.lakshay.newsapp.data.BaseDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRemoteDataSource @Inject constructor(
    private val newsService: NewsService
) : BaseDataSource() {

    suspend fun fetchNews(country: String, apiKey: String, pageSize: Int) = getResult {
        newsService.fetchNews(country, apiKey, pageSize)
    }
}