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
import com.example.case_study_global_news.ui.GlobalNewsApp
import com.example.case_study_global_news.ui.search.SearchActivity
import com.example.case_study_global_news.ui.categories.CategoryActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    // View Model
    private val viewModel by viewModels<MainViewModel> {
        val globalNewsApplication = application as GlobalNewsApp
        val apiService = globalNewsApplication.serviceLocator.apiService
        val mainRepository = MainRepository(apiService)
        MainViewModelFactory(mainRepository)
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.homeRecyclerView.layoutManager = LinearLayoutManager(this)

        val adapter = MainCardAdapter(OnArticleClickListener { article ->
            viewModel.onArticleClick(article)
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