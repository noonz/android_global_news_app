package com.example.case_study_global_news.ui.categories

import android.os.Bundle
import androidx.lifecycle.*
import androidx.savedstate.SavedStateRegistryOwner
import com.example.case_study_global_news.data.MainRepository
import com.example.case_study_global_news.data.network.models.CategoryInfo
import com.example.case_study_global_news.ui.BundleKeys
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class CategoryViewModel(
    private val mainRepository: MainRepository,
    private val state: SavedStateHandle
) : ViewModel() {

    val categoryList = mainRepository.categories

    private val _navigateToCategorySelectedWebView = MutableLiveData<CategoryInfo?>();
    val navigateToCategoryWebView: LiveData<CategoryInfo?> get() = _navigateToCategorySelectedWebView;

    // keyword passed from category activity button click
    private val keyword: String get() = state.get(BundleKeys.KEYWORD) ?: ""

    private val _navigateToCategories = MutableLiveData<CategoryInfo?>()

    fun onCategorySelectedClick(category: CategoryInfo) {
        _navigateToCategorySelectedWebView.value = category
    }

    fun onCategorySelectedClickComplete() {
        _navigateToCategorySelectedWebView.value = null
    }

    fun onCategoryClick(category: CategoryInfo) {
        _navigateToCategories.value = category
    }

    init {
        viewModelScope.launch {
            mainRepository.getNewsCategories(keyword)
        }
    }
}

class CategoryViewModelFactory(
    private val mainRepository: MainRepository,
    owner: SavedStateRegistryOwner,
    defaultArgs: Bundle?
) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {

    @Suppress("UNCHECKED_CASTS")
    override fun <T : ViewModel?> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        if (modelClass.isAssignableFrom(CategoryViewModel::class.java)) {
            return CategoryViewModel(mainRepository, handle) as T
        }

        throw  IllegalArgumentException("Invalid view model")
    }

}