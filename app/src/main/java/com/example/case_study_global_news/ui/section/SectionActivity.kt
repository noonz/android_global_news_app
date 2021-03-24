package com.example.case_study_global_news.ui.section

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.case_study_global_news.R
import com.example.case_study_global_news.databinding.ActivitySectionBinding
import com.example.case_study_global_news.ui.BundleKeys
import com.example.case_study_global_news.ui.main.MainActivity
import com.example.case_study_global_news.ui.search.SearchActivity
import com.google.android.material.bottomnavigation.BottomNavigationView


class SectionActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySectionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySectionBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.buttonBusiness.setOnClickListener {
            val intent = Intent(this, SectionsNewsDetailsActivity::class.java)
            intent.putExtra(BundleKeys.KEYWORD, "business")
            startActivity(intent)
        }

        binding.buttonEntertainment.setOnClickListener {
            val intent = Intent(this, SectionsNewsDetailsActivity::class.java)
            intent.putExtra(BundleKeys.KEYWORD, "entertainment")
            startActivity(intent)
        }

        binding.buttonGeneral.setOnClickListener {
            val intent = Intent(this, SectionsNewsDetailsActivity::class.java)
            intent.putExtra(BundleKeys.KEYWORD, "general")
            startActivity(intent)
        }

        binding.buttonHealth.setOnClickListener {
            val intent = Intent(this, SectionsNewsDetailsActivity::class.java)
            intent.putExtra(BundleKeys.KEYWORD, "health")
            startActivity(intent)
        }

        binding.buttonScience.setOnClickListener {
            val intent = Intent(this, SectionsNewsDetailsActivity::class.java)
            intent.putExtra(BundleKeys.KEYWORD, "science")
            startActivity(intent)
        }

        binding.buttonSports.setOnClickListener {
            val intent = Intent(this, SectionsNewsDetailsActivity::class.java)
            intent.putExtra(BundleKeys.KEYWORD, "sports")
            startActivity(intent)
        }

        binding.buttonTechnology.setOnClickListener {
            val intent = Intent(this, SectionsNewsDetailsActivity::class.java)
            intent.putExtra(BundleKeys.KEYWORD, "technology")
            startActivity(intent)
        }
        val mOnNavigationItemSelectedListener =
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
                    R.id.sections -> {
                        val intent = Intent(this, SectionActivity::class.java)
                        startActivity(intent)

                        return@OnNavigationItemSelectedListener true
                    }
                }
                false
            }

        binding.bottomNavigationView.setOnNavigationItemSelectedListener(
            mOnNavigationItemSelectedListener
        )
    }


}