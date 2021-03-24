package com.example.case_study_global_news.ui.search

import androidx.lifecycle.*
import com.example.case_study_global_news.data.MainRepository
import com.example.case_study_global_news.data.network.models.Articles
import com.example.case_study_global_news.ui.main.MainViewModel
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class SearchViewModel (private val mainRepository: MainRepository) : ViewModel(){
    val resultList = mainRepository.searchResults

    private val _navigateToSearchResults = MutableLiveData<Articles?>();
    val navigateToSearchResult: LiveData<Articles?> get() = _navigateToSearchResults;

    fun onResultClick(articles: Articles) {
        _navigateToSearchResults.value = articles
    }

    fun onNavigateToSearchResultsComplete() {
        _navigateToSearchResults.value = null
    }

    init {
        viewModelScope.launch {
            mainRepository.getSearchResults("covid")
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