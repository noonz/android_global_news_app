package com.example.case_study_global_news.ui.section

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.case_study_global_news.R
import com.example.case_study_global_news.data.MainRepository
import com.example.case_study_global_news.data.network.models.Articles
import com.example.case_study_global_news.databinding.ActivityMainBinding
import com.example.case_study_global_news.databinding.ActivitySectionsNewsDetailsBinding
import com.example.case_study_global_news.ui.BundleKeys
import com.example.case_study_global_news.ui.NewsApp
import com.example.case_study_global_news.ui.main.*

class SectionsNewsDetailsActivity : AppCompatActivity() {


    private val viewModel by viewModels<SectionViewModel> {
        val newsInfoApplication = application as NewsApp
        val apiService = newsInfoApplication.serviceLocator.apiService
        val mainRepository = MainRepository(apiService)
        SectionViewModelFactory(mainRepository, this, intent.extras)
    }
    private lateinit var binding: ActivitySectionsNewsDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySectionsNewsDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.categoryNewsDetails.layoutManager = LinearLayoutManager(this)

        val adapter = SectionNewsDetailsAdapter(OnSectionsNewsClickListener { sectionNews ->
            viewModel.onSectionClick(sectionNews)
            viewModel.onSectionDetailClick(sectionNews)
        })



        binding.categoryNewsDetails.adapter = adapter;

        viewModel.newsCategories.observe(this) {
            adapter.data = it.sources
            adapter.notifyDataSetChanged()
        }

        viewModel.navigateToSectionWebView.observe(this) { categories ->
            categories?.let {
                val intent = Intent(this, WebViewActivity::class.java).apply {
                    putExtra(BundleKeys.NEWS_NAME, categories.url)
                }
                startActivity(intent)
                viewModel.onSectionDetailClickComplete()
            }
        }

    }
}