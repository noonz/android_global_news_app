package com.example.case_study_global_news.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.case_study_global_news.R
import com.example.case_study_global_news.data.MainRepository
import com.example.case_study_global_news.databinding.ActivityMainBinding
import com.example.case_study_global_news.ui.BaseActivity
import com.example.case_study_global_news.ui.BundleKeys
import com.example.case_study_global_news.ui.GlobalNewsApp
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding

    // bottomNav
    override val bottomNav: BottomNavigationView
        get() = binding.bottomNavigationView
    override val selectedItemId: Int get() = R.id.home

    // View Model
    private val viewModel by viewModels<MainViewModel> {
        val globalNewsApplication = application as GlobalNewsApp
        val apiService = globalNewsApplication.serviceLocator.apiService
        val mainRepository = MainRepository(apiService)
        MainViewModelFactory(mainRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.setTitle("Home")


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.homeRecyclerView.layoutManager = LinearLayoutManager(this)

        val adapter = MainCardAdapter(OnArticleClickListener { articleInfo ->
            viewModel.onArticleClick(articleInfo)
        })
        binding.homeRecyclerView.adapter = adapter

        viewModel.articleList.observe(this) {
            adapter.articleInfoList = it.articles
            adapter.notifyDataSetChanged()
        }

        // navigate to article web view here
        viewModel.navigateToArticleWebView.observe(this) { articleInfo ->
            articleInfo?.let {
                val intent = Intent(this, WebViewActivity::class.java).apply {
                    putExtra(BundleKeys.ARTICLE_URL, articleInfo.url)
                }
                startActivity(intent)
                viewModel.onNavigateArticleComplete()
            }
        }
    }
}