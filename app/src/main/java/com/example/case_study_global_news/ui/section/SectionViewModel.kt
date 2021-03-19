package com.example.case_study_global_news.ui.section

import androidx.lifecycle.*
import com.example.case_study_global_news.data.MainRepository
import com.example.case_study_global_news.data.network.models.Categories
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class SectionViewModel(private val mainRepository: MainRepository): ViewModel() {

    val newsCategories = mainRepository.newsCategories

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
            mainRepository.getNewsCategories()
        }
    }
}

class SectionViewModelFactory(private val mainRepository: MainRepository): ViewModelProvider.Factory{

    @Suppress("UNCHECKED_CASTS")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(SectionViewModel::class.java)){
            return  SectionViewModel(mainRepository) as T
        }

        throw IllegalArgumentException("View model cannot be assigned")
    }


}