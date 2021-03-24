package com.example.case_study_global_news.data.network

import com.example.case_study_global_news.data.network.models.Categories
import com.example.case_study_global_news.data.network.models.Articles
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    // base-url https://newsapi.org/v2/

    // Endpoint #1: top-headlines?language=en
    @GET("top-headlines?language=en&apiKey=86209e63f5784c66b33502c1d0bc66fe")
    suspend fun fetchTopHeadlines(): Articles

    // Endpoint #2: sources?apiKey=86209e63f5784c66b33502c1d0bc66fe&category=business
    // retrofit appends the query with the value and arguments
    @GET("sources?apiKey=86209e63f5784c66b33502c1d0bc66fe&language=en")
    suspend fun fetchCategories(@Query("category") category: String): Categories

    // Endpoint #3: everything?apiKey=API_KEY&q=SEARCHPARAM
    @GET("everything?apiKey=86209e63f5784c66b33502c1d0bc66fe&language=en")
    suspend fun fetchSearchResults(@Query("q") q: String): Articles
}