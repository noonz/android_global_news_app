package com.example.case_study_global_news.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.case_study_global_news.data.network.ApiService
import com.example.case_study_global_news.data.network.models.Categories
import com.example.case_study_global_news.data.network.models.Articles
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception

class MainRepository(private val apiService: ApiService) {
    private val _articles = MutableLiveData<Articles>()
    val articles: LiveData<Articles> get() = _articles

    private val _categories = MutableLiveData<Categories>()
    val categories: LiveData<Categories> get() = _categories

    private val _searchResults = MutableLiveData<Articles>()
    val searchResults: LiveData<Articles> get() = _searchResults

    // Loads the list of search results and saves it to LiveData
    suspend fun getSearchResults(keyword: String) {
        val searchResults: Articles? = try {
            apiService.fetchSearchResults(keyword)
        } catch (e: Exception) {
            null
        }

        _searchResults.value = searchResults ?: null
    }

    // Loads the list of articles and saves it to LiveData
    suspend fun getNewsArticles() {
        val articles: Articles? = try {
            apiService.fetchTopHeadlines()
        } catch (e: Exception) {
            null
        }

        _articles.value = articles ?: null
    }

    // Loads the list of categories and saves it to LiveData
    suspend fun getNewsCategories(keyword: String) {
        val categories: Categories? = try {
            apiService.fetchCategories(keyword)
        }  catch (e: Exception) {
            null
        }

        _categories.value = categories ?: null
    }
}