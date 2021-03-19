package com.example.case_study_global_news.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.case_study_global_news.R
import com.example.case_study_global_news.data.MainRepository
import com.example.case_study_global_news.data.network.models.NewsInfo
import com.example.case_study_global_news.databinding.ActivityMainBinding
import com.example.case_study_global_news.ui.BundleKeys
import com.example.case_study_global_news.ui.NewsApp
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

        viewModel.newsList.observe(this){
            //var list = it as NewsInfo
            adapter.data = it.articles
            adapter.notifyDataSetChanged()
        }

        viewModel.navigateToArticles.observe(this){articles ->
            articles?.let {
                val intent = Intent(this, NewsDetailsActivity::class.java).apply {
                    putExtra(BundleKeys.NEWS_NAME, articles.url)
                }
                startActivity(intent)
                viewModel.onNavigateToDetailComplete()
            }
        }
    }
}