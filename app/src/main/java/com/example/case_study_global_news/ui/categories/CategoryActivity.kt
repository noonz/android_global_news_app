package com.example.case_study_global_news.ui.categories

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.case_study_global_news.R
import com.example.case_study_global_news.databinding.ActivityCategoryBinding
import com.example.case_study_global_news.ui.BaseActivity
import com.example.case_study_global_news.ui.BundleKeys
import com.example.case_study_global_news.ui.main.MainCardAdapter
import com.example.case_study_global_news.ui.main.OnArticleClickListener
import com.google.android.material.bottomnavigation.BottomNavigationView

class CategoryActivity : BaseActivity() {
    private lateinit var binding: ActivityCategoryBinding

    // bottomNav
    override val bottomNav: BottomNavigationView
        get() = findViewById(R.id.bottomNavigationView)
    override val selectedItemId: Int get() = R.id.categories

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.setTitle("Categories")

        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.categoryListView.layoutManager = LinearLayoutManager(this)


        val adapter = CategoryListAdapter(OnCategoryClickListener { category ->
            val intent = Intent(this, CategorySelectedActivity::class.java)
            intent.putExtra(BundleKeys.KEYWORD, category)
            startActivity(intent)
        })
        binding.categoryListView.adapter = adapter

    }
}