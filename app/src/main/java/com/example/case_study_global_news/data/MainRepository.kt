package com.example.case_study_global_news.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import com.example.case_study_global_news.data.database.NewsArticleDatabase
import com.example.case_study_global_news.data.domain.ArticleInfo
import com.example.case_study_global_news.data.network.ApiService
import com.example.case_study_global_news.data.network.models.Categories
import com.example.case_study_global_news.data.network.models.Articles
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception

class MainRepository(private val apiService: ApiService, private val database: NewsArticleDatabase) {

     val searchResults: Flow<List<ArticleInfo>> = database.getArticleDao().getSearchArticles().map {localArticles ->
         localArticles.map { localArticleInfo ->
             localArticleInfo.toDomainArticleInfo()
         }
     }

    val articles: LiveData<List<ArticleInfo>> = database.getArticleDao().getArticleInfos().map {localArticles ->
        localArticles.map { localArticleInfo ->
            localArticleInfo.toDomainArticleInfo()
        }
    }

    private val _categories = MutableLiveData<Categories>()
    val categories: LiveData<Categories> get() = _categories

//    private val _searchResults = MutableLiveData<Articles>()
//    val searchResults: LiveData<Articles> get() = _searchResults

    // Loads the list of search results and saves it to LiveData
    suspend fun getSearchResults(keyword: String?) {
        val searchResults: Articles? = try {
            apiService.fetchSearchResults(keyword)
        } catch (e: Exception) {
            null
        }

//        _searchResults.value = searchResults ?: null

        searchResults?.let {
            withContext(Dispatchers.IO){
                // clear database, then reload new data????.....
                database.getArticleDao().deleteAll()
                database.getArticleDao().insertAll(searchResults.articles.map {
                    it.toLocalArticleInfo()
                })
            }
        }
    }

    // Loads the list of articles and saves it to LiveData
    suspend fun getNewsArticles() {
        val articles: Articles? = try {
            apiService.fetchTopHeadlines()
        } catch (e: Exception) {
            null
        }
        articles?.let {
            withContext(Dispatchers.IO){
                // clear database, then reload new data????.....
                database.getArticleDao().deleteAll()
                database.getArticleDao().insertAll(articles.articles.map {
                    it.toLocalArticleInfo()
                })
            }
        }
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