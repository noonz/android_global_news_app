package com.example.case_study_global_news.data.network

import com.example.case_study_global_news.data.network.models.NewsCategories
import com.example.case_study_global_news.data.network.models.NewsInfo
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {


    // base-url https://newsapi.org/v2/
    @GET("top-headlines?language=en&apiKey=86209e63f5784c66b33502c1d0bc66fe")
    suspend fun fetchTopHeadlines(): NewsInfo

    //sources?apiKey=86209e63f5784c66b33502c1d0bc66fe&category=business
    // retrofit appends the query with the value and arguments
    @GET("sources?apiKey=86209e63f5784c66b33502c1d0bc66fe&language=en")
    suspend fun fetchNewsCategories(@Query("category") category: String): NewsCategories
}