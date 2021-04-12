package com.example.case_study_global_news.ui.categories

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.case_study_global_news.data.MainRepository
import com.example.case_study_global_news.databinding.ActivityCategorySelectedBinding
import com.example.case_study_global_news.ui.BundleKeys
import com.example.case_study_global_news.ui.GlobalNewsApp
import com.example.case_study_global_news.ui.main.*

class CategorySelectedActivity : AppCompatActivity() {
    // View Model
    private val viewModel by viewModels<CategoryViewModel> {
        val globalNewsApplication = application as GlobalNewsApp
        val apiService = globalNewsApplication.serviceLocator.apiService
        val database  = globalNewsApplication.serviceLocator.database
        val mainRepository = MainRepository(apiService,database)
        CategoryViewModelFactory(mainRepository, this, intent.extras)
    }
    private lateinit var binding: ActivityCategorySelectedBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        supportActionBar?.setTitle(intent.extras?.get("keyword").toString().capitalize())


        super.onCreate(savedInstanceState)
        binding = ActivityCategorySelectedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.selectedCategoryRecyclerView.layoutManager = LinearLayoutManager(this)

        val adapter = CategorySelectedAdapter(OnCategorySelectedClickListener { categorySelectedInfo ->
            viewModel.onCategoryClick(categorySelectedInfo)
            viewModel.onCategorySelectedClick(categorySelectedInfo)
        })

        binding.selectedCategoryRecyclerView.adapter = adapter

        viewModel.categoryList.observe(this) {
            adapter.categoryInfoList = it.categories
            adapter.notifyDataSetChanged()
        }

        // navigate to selected category item web view here
        viewModel.navigateToCategoryWebView.observe(this) { categoryInfo ->
            categoryInfo?.let {
                val intent = Intent(this, WebViewActivity::class.java).apply {
                    putExtra(BundleKeys.ARTICLE_URL, categoryInfo.url)
                }
                startActivity(intent)
                viewModel.onCategorySelectedClickComplete()
            }
        }
    }
}