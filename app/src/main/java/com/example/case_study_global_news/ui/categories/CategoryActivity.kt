package com.example.case_study_global_news.ui.categories

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.case_study_global_news.R
import com.example.case_study_global_news.databinding.ActivityCategoryBinding
import com.example.case_study_global_news.ui.BundleKeys
import com.example.case_study_global_news.ui.main.MainActivity
import com.example.case_study_global_news.ui.search.SearchActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class CategoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCategoryBinding

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