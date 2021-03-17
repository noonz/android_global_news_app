package com.example.case_study_global_news.data.network

import com.example.case_study_global_news.data.network.models.NewsInfo
import retrofit2.http.GET

interface ApiService {


    // base-url https://newsapi.org/v2/
    @GET("top-headlines?language=en&apiKey=86209e63f5784c66b33502c1d0bc66fe")
    suspend fun fetchTopHeadlines(): NewsInfo
}