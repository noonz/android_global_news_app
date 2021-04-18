package com.example.case_study_global_news.ui.search

import androidx.lifecycle.*
import androidx.lifecycle.map
import com.example.case_study_global_news.data.MainRepository
import com.example.case_study_global_news.data.domain.ArticleInfo
import com.example.case_study_global_news.data.network.models.WebArticleInfo
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class SearchViewModel (private val mainRepository: MainRepository) : ViewModel(){

    private val _navigateToSearchResults = MutableLiveData<ArticleInfo?>();
    val navigateToSearchResult: LiveData<ArticleInfo?> get() = _navigateToSearchResults;

    // search box stuff
    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val searchKey = MutableStateFlow<String?>(null)

    fun onNewSearchQuery(key: String) {
        searchKey.value = key
    }

    var resultList = searchKey
        .onEach {
            _isLoading.value = true
        }
        .debounce { if (it.isNullOrBlank()) 0 else 1000 }
        .flatMapLatest { searchKey ->
            mainRepository.getSearchResults(searchKey)
            mainRepository.searchResults.map { articleInfos ->
                articleInfos.filter { article ->
                    (searchKey.isNullOrBlank() || article.title.contains(
                        searchKey,
                        ignoreCase = true
                    ))
                }
            }
        }
        .onEach {
            _isLoading.value = false
        }
        .asLiveData()

    fun onSearchResultClick(articles: ArticleInfo) {
        _navigateToSearchResults.value = articles
    }

    fun onNavigateToSearchResultsComplete() {
        _navigateToSearchResults.value = null
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