package com.example.case_study_global_news.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.case_study_global_news.R
import com.example.case_study_global_news.data.MainRepository
import com.example.case_study_global_news.data.network.models.NewsInfo
import com.example.case_study_global_news.ui.NewsApp
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel> {
        val newsInfoApplication = application as NewsApp
        val apiService = newsInfoApplication.serviceLocator.apiService
        val mainRepository = MainRepository(apiService)
        MainViewModelFactory(mainRepository)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val newsRecyclerView:RecyclerView = findViewById(R.id.news_recycler_view)
        newsRecyclerView.layoutManager = LinearLayoutManager(this)

        val adapter = NewsCardAdapter()
        newsRecyclerView.adapter = adapter

        viewModel.newsList.observe(this){
            var list = it as NewsInfo
            adapter.data = list.articles
            adapter.notifyDataSetChanged()
        }

    }
}