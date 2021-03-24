package com.example.case_study_global_news.ui.search

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.case_study_global_news.R
import com.example.case_study_global_news.data.MainRepository
import com.example.case_study_global_news.databinding.ActivitySearchBinding
import com.example.case_study_global_news.ui.NewsApp
import com.example.case_study_global_news.ui.main.*
import com.example.case_study_global_news.ui.section.SectionActivity
import com.google.android.material.bottomnavigation.BottomNavigationView


class SearchActivity : AppCompatActivity() {
    private val viewModel by viewModels<SearchViewModel> {
        val newsInfoApplication = application as NewsApp
        val apiService = newsInfoApplication.serviceLocator.apiService
        val mainRepository = MainRepository(apiService)
        SearchViewModelFactory(mainRepository)
    }
    private lateinit var binding: ActivitySearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.searchRecyclerView.layoutManager = LinearLayoutManager(this)

        val adapter = SearchActivityListAdapter(OnResultClickListener { article ->
            viewModel.onResultClick(article)
        })
        binding.searchRecyclerView.adapter = adapter

        viewModel.resultList.observe(this) {
            adapter.data = it.articles
            adapter.notifyDataSetChanged()
        }
        val mOnNavigationItemSelectedListener =
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
                    R.id.sections -> {
                        val intent = Intent(this, SectionActivity::class.java)
                        startActivity(intent)

                        return@OnNavigationItemSelectedListener true
                    }
                }
                false
            }

        binding.bottomNavigationView.setOnNavigationItemSelectedListener(
            mOnNavigationItemSelectedListener
        )

    }


}