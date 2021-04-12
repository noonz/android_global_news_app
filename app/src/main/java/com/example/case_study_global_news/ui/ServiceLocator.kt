package com.example.case_study_global_news.ui

import android.content.Context
import androidx.room.Room
import com.example.case_study_global_news.data.database.NewsArticleDatabase
import com.example.case_study_global_news.data.network.ApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class ServiceLocator(applicationContext: Context) {
    companion object {
        private const val BASE_URL = "https://newsapi.org/v2/"
        private const val DATABASE_NAME = "newsArticle_database"
    }

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    val apiService: ApiService = retrofit.create(ApiService::class.java)

    val database = Room.databaseBuilder(
        applicationContext,
        NewsArticleDatabase::class.java,
        DATABASE_NAME
    ).fallbackToDestructiveMigration().build()
}