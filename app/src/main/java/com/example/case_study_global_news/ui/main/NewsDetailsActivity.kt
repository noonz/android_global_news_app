package com.example.case_study_global_news.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import com.example.case_study_global_news.R
import com.example.case_study_global_news.databinding.ActivityMainBinding
import com.example.case_study_global_news.databinding.ActivityNewsDetailsBinding
import com.example.case_study_global_news.ui.BundleKeys

class NewsDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewsDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNewsDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadNewsWebSite(intent)
    }

    private fun loadNewsWebSite(intent: Intent){
        val action = intent.action
        val data = intent.dataString

        val newsUrl = if (action == Intent.ACTION_VIEW && data != null){
            data
        }else intent.getStringExtra(BundleKeys.NEWS_NAME) ?: ""

        binding.newsWebView.loadUrl(newsUrl)
    }
}