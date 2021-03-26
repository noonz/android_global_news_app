package com.example.case_study_global_news.ui.search

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.case_study_global_news.R
import com.example.case_study_global_news.data.MainRepository
import com.example.case_study_global_news.databinding.ActivitySearchBinding
import com.example.case_study_global_news.ui.BaseActivity
import com.example.case_study_global_news.ui.BundleKeys
import com.example.case_study_global_news.ui.GlobalNewsApp
import com.example.case_study_global_news.ui.main.*
import com.google.android.material.bottomnavigation.BottomNavigationView


class SearchActivity : BaseActivity() {
    private lateinit var binding: ActivitySearchBinding

    // bottomNav
    override val bottomNav: BottomNavigationView
        get() = findViewById(R.id.bottomNavigationView)
    override val selectedItemId: Int get() = R.id.search

    // View model
    private val viewModel by viewModels<SearchViewModel> {
        val globalNewsApplication = application as GlobalNewsApp
        val apiService = globalNewsApplication.serviceLocator.apiService
        val mainRepository = MainRepository(apiService)
        SearchViewModelFactory(mainRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.searchRecyclerView.layoutManager = LinearLayoutManager(this)

        val adapter = SearchActivityListAdapter(OnResultClickListener { articleInfo ->
            viewModel.onSearchResultClick(articleInfo)
        })

        binding.searchRecyclerView.adapter = adapter

        viewModel.searchResultList.observe(this) {
            adapter.searchResultInfoList = it.articles
            adapter.notifyDataSetChanged()
        }

        viewModel.navigateToSearchResult.observe(this) { articleInfo ->
            articleInfo?.let {
                val intent = Intent(this, WebViewActivity::class.java).apply {
                    putExtra(BundleKeys.ARTICLE_URL, articleInfo.url)
                }
                startActivity(intent)
                viewModel.onNavigateToSearchResultsComplete()
            }
        }
    }
}