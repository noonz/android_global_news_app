package com.example.case_study_global_news.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.case_study_global_news.data.network.ApiService
import com.example.case_study_global_news.data.network.models.Articles
import com.example.case_study_global_news.data.network.models.NewsInfo
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception

class MainRepository(private val apiService: ApiService) {
    private val _newsArticles = MutableLiveData<List<Articles>>()
    val newsArticles: LiveData<List<Articles>> get() = _newsArticles

    suspend fun getNewsArticles() {
        val newsArticles: NewsInfo? = try {
            apiService.fetchTopHeadlines()
        } catch (e: HttpException){
            null
        } catch (e: IOException) {
            null
        } catch (e: Exception) {
            null
        }

        _newsArticles.value = newsArticles?.articles ?: emptyList()
    }
}