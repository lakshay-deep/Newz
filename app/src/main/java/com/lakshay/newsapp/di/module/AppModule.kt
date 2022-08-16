package com.lakshay.newsapp.di.module

import android.content.Context
import android.net.ConnectivityManager
import com.lakshay.newsapp.data.network.NewsService
import com.lakshay.newsapp.data.repository.NewsRepository
import com.lakshay.newsapp.data.repository.NewsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    companion object{

        @Provides
        @Singleton
        fun provideMovieService(retrofit: Retrofit): NewsService{
            return retrofit.create(NewsService::class.java)
        }

        @Provides
        @Singleton
        fun provideConnectivityManager(@ApplicationContext context: Context): ConnectivityManager {
            return context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        }
    }

    @Binds
    @Singleton
    abstract fun provideMovieRepository(movieRepositoryImpl: NewsRepositoryImpl): NewsRepository
}