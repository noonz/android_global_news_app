package com.example.case_study_global_news.ui.section

import android.os.Bundle
import androidx.lifecycle.*
import androidx.savedstate.SavedStateRegistryOwner
import com.example.case_study_global_news.data.MainRepository
import com.example.case_study_global_news.data.network.models.Categories
import com.example.case_study_global_news.ui.BundleKeys
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class SectionViewModel(private val mainRepository: MainRepository, private val state: SavedStateHandle): ViewModel() {

    val newsCategories = mainRepository.newsCategories

    private val keyword: String get() = state.get(BundleKeys.KEYWORD) ?: ""

    private val _navigateToCategories = MutableLiveData<Categories?>();
    val navigateToCategories: LiveData<Categories?> get () = _navigateToCategories;

    fun onSectionClick(category: Categories){
        _navigateToCategories.value = category
    }
    fun onNavigateToSectionComplete(){
        _navigateToCategories.value = null
    }
    init {
        viewModelScope.launch {
            mainRepository.getNewsCategories(keyword)
        }
    }
}

class SectionViewModelFactory(private val mainRepository: MainRepository, owner: SavedStateRegistryOwner, defaultArgs: Bundle?): AbstractSavedStateViewModelFactory(owner,defaultArgs){

    @Suppress("UNCHECKED_CASTS")
    override fun <T : ViewModel?> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        if(modelClass.isAssignableFrom(SectionViewModel::class.java)){
            return SectionViewModel(mainRepository, handle) as T
        }

        throw  IllegalArgumentException("Invalid view model")
    }

}