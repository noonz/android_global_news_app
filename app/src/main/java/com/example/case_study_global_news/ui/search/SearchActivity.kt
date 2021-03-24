package com.example.case_study_global_news.ui.search

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.case_study_global_news.R
import com.example.case_study_global_news.data.MainRepository
import com.example.case_study_global_news.databinding.ActivitySearchBinding
import com.example.case_study_global_news.ui.BundleKeys
import com.example.case_study_global_news.ui.GlobalNewsApp
import com.example.case_study_global_news.ui.categories.CategoryActivity
import com.example.case_study_global_news.ui.main.*
import com.google.android.material.bottomnavigation.BottomNavigationView


class SearchActivity : AppCompatActivity() {
    // View model
    private val viewModel by viewModels<SearchViewModel> {
        val globalNewsApplication = application as GlobalNewsApp
        val apiService = globalNewsApplication.serviceLocator.apiService
        val mainRepository = MainRepository(apiService)
        SearchViewModelFactory(mainRepository)
    }

    private lateinit var binding: ActivitySearchBinding

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

        // set bottom nav listener
        binding.bottomNavigationView.setOnNavigationItemSelectedListener(
            mOnNavigationItemSelectedListener
        )
    }

    // bottom nav view listener
    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.home -> {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)

                    return@OnNavigationItemSelectedListener true
                }
                R.id.search -> {
                    val intent = Intent(this, SearchActivity::class.java)
                    startActivity(intent)

                    return@OnNavigationItemSelectedListener true
                }
                R.id.categories -> {
                    val intent = Intent(this, CategoryActivity::class.java)
                    startActivity(intent)

                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }
}