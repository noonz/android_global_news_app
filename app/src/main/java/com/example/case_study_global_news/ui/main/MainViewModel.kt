package com.example.case_study_global_news.ui.main

import androidx.lifecycle.*
import com.example.case_study_global_news.data.MainRepository
import com.example.case_study_global_news.data.network.models.ArticleInfo
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {
    val articleList = mainRepository.articles

    private val _navigateToArticleWebView = MutableLiveData<ArticleInfo?>();
    val navigateToArticleWebView: LiveData<ArticleInfo?> get() = _navigateToArticleWebView;

    fun onArticleClick(articles: ArticleInfo) {
        _navigateToArticleWebView.value = articles
    }

    fun onNavigateArticleComplete() {
        _navigateToArticleWebView.value = null
    }

    init {
        viewModelScope.launch {
            mainRepository.getNewsArticles()
        }
    }
}

class MainViewModelFactory(private val mainRepository: MainRepository) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CASTS")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(mainRepository) as T
        }

        throw IllegalArgumentException("View model cannot be assigned")
    }


}