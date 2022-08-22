package com.lakshay.newsapp.data.repository

import com.lakshay.newsapp.data.network.NewsRemoteDataSource
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsRemoteDataSource: NewsRemoteDataSource
) : NewsRepository {
    override suspend fun fetchNews(
        country: String,
        apiKey: String,
        pageSize: Int
    ) = newsRemoteDataSource.fetchNews(country,apiKey, pageSize)
}