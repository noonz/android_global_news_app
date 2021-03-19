package com.example.case_study_global_news.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.load.engine.executor.GlideExecutor.UncaughtThrowableStrategy.LOG
import com.example.case_study_global_news.data.network.ApiService
import com.example.case_study_global_news.data.network.models.Articles
import com.example.case_study_global_news.data.network.models.NewsCategories
import com.example.case_study_global_news.data.network.models.NewsInfo
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception
import java.lang.IllegalArgumentException
import kotlin.math.log

class MainRepository(private val apiService: ApiService) {
    private val _newsArticles = MutableLiveData<NewsInfo>()
    val newsArticles: LiveData<NewsInfo> get() = _newsArticles

    private val _newsCategories = MutableLiveData<NewsCategories>()
    val newsCategories: LiveData<NewsCategories> get() = _newsCategories

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

        _newsArticles.value = newsArticles ?: null
    }

    suspend fun getNewsCategories(keyword: String) {
        val newsCategories: NewsCategories? = try {
            apiService.fetchNewsCategories(keyword)
        } catch (e: HttpException){
            null
        } catch (e: IOException) {
            null
        } catch (e: Exception) {
          null
        }

        _newsCategories.value = newsCategories ?: null
    }
}