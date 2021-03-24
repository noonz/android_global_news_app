package com.example.case_study_global_news.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.case_study_global_news.R
import com.example.case_study_global_news.data.MainRepository
import com.example.case_study_global_news.databinding.ActivityMainBinding
import com.example.case_study_global_news.ui.BundleKeys
import com.example.case_study_global_news.ui.NewsApp
import com.example.case_study_global_news.ui.search.SearchActivity
import com.example.case_study_global_news.ui.section.SectionActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel> {
        val newsInfoApplication = application as NewsApp
        val apiService = newsInfoApplication.serviceLocator.apiService
        val mainRepository = MainRepository(apiService)
        MainViewModelFactory(mainRepository)
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.newsRecyclerView.layoutManager = LinearLayoutManager(this)


        val adapter = NewsCardAdapter(OnNewsClickListener { article ->
            viewModel.onArticleClick(article)
        })
        binding.newsRecyclerView.adapter = adapter

        viewModel.newsList.observe(this) {
            adapter.data = it.articles
            adapter.notifyDataSetChanged()
        }

        viewModel.navigateToArticles.observe(this) { articles ->
            articles?.let {
                val intent = Intent(this, WebViewActivity::class.java).apply {
                    putExtra(BundleKeys.NEWS_NAME, articles.url)
                }
                startActivity(intent)
                viewModel.onNavigateToDetailComplete()
            }
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