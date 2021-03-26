package com.example.case_study_global_news.ui.categories

import android.content.Intent
import android.os.Bundle
import com.example.case_study_global_news.R
import com.example.case_study_global_news.databinding.ActivityCategoryBinding
import com.example.case_study_global_news.ui.BaseActivity
import com.example.case_study_global_news.ui.BundleKeys
import com.google.android.material.bottomnavigation.BottomNavigationView

class CategoryActivity : BaseActivity() {
    private lateinit var binding: ActivityCategoryBinding

    // bottomNav
    override val bottomNav: BottomNavigationView
        get() = findViewById(R.id.bottomNavigationView)
    override val selectedItemId: Int get() = R.id.categories

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonBusiness.setOnClickListener {
            val intent = Intent(this, CategorySelectedActivity::class.java)
            intent.putExtra(BundleKeys.KEYWORD, "business")
            startActivity(intent)
        }

        binding.buttonEntertainment.setOnClickListener {
            val intent = Intent(this, CategorySelectedActivity::class.java)
            intent.putExtra(BundleKeys.KEYWORD, "entertainment")
            startActivity(intent)
        }

        binding.buttonGeneral.setOnClickListener {
            val intent = Intent(this, CategorySelectedActivity::class.java)
            intent.putExtra(BundleKeys.KEYWORD, "general")
            startActivity(intent)
        }

        binding.buttonHealth.setOnClickListener {
            val intent = Intent(this, CategorySelectedActivity::class.java)
            intent.putExtra(BundleKeys.KEYWORD, "health")
            startActivity(intent)
        }

        binding.buttonScience.setOnClickListener {
            val intent = Intent(this, CategorySelectedActivity::class.java)
            intent.putExtra(BundleKeys.KEYWORD, "science")
            startActivity(intent)
        }

        binding.buttonSports.setOnClickListener {
            val intent = Intent(this, CategorySelectedActivity::class.java)
            intent.putExtra(BundleKeys.KEYWORD, "sports")
            startActivity(intent)
        }

        binding.buttonTechnology.setOnClickListener {
            val intent = Intent(this, CategorySelectedActivity::class.java)
            intent.putExtra(BundleKeys.KEYWORD, "technology")
            startActivity(intent)
        }
    }
}