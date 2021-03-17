package com.example.case_study_global_news.ui.main

import androidx.lifecycle.*
import com.example.case_study_global_news.data.MainRepository
import com.example.case_study_global_news.data.network.models.Articles
import com.example.case_study_global_news.data.network.models.NewsInfo
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class MainViewModel(private val mainRepository: MainRepository): ViewModel() {

    val newsList = mainRepository.newsArticles

    private val _navigateToArticles = MutableLiveData<Articles?>();
    val navigateToArticles: LiveData<Articles?>get () = _navigateToArticles;

    init {
        viewModelScope.launch { mainRepository.getNewsArticles() }
    }

    fun onArticleClick(articles: Articles){
        //todo
    }
}

class MainViewModelFactory(private val mainRepository: MainRepository): ViewModelProvider.Factory{

    @Suppress("UNCHECKED_CASTS")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MainViewModel::class.java)){
            return  MainViewModel(mainRepository) as T
        }

        throw IllegalArgumentException("View model cannot be assigned")
    }


}