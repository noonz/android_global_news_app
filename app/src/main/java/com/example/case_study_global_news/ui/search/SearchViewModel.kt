package com.example.case_study_global_news.ui.search

import androidx.lifecycle.*
import com.example.case_study_global_news.data.MainRepository
import com.example.case_study_global_news.data.network.models.WebArticleInfo
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class SearchViewModel (private val mainRepository: MainRepository) : ViewModel(){
    val searchResultList = mainRepository.searchResults

    private val _navigateToSearchResults = MutableLiveData<WebArticleInfo?>();
    val navigateToSearchResult: LiveData<WebArticleInfo?> get() = _navigateToSearchResults;

    fun onSearchResultClick(articles: WebArticleInfo) {
        _navigateToSearchResults.value = articles
    }

    fun onNavigateToSearchResultsComplete() {
        _navigateToSearchResults.value = null
    }

    init {
        viewModelScope.launch {
            mainRepository.getSearchResults("biden") // TODO: change keyword to user search input
        }
    }
}

class SearchViewModelFactory(private val mainRepository: MainRepository) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CASTS")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchViewModel::class.java)) {
            return SearchViewModel(mainRepository) as T
        }
        throw IllegalArgumentException("View model cannot be assigned")
    }
}