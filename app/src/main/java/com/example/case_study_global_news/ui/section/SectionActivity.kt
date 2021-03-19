package com.example.case_study_global_news.ui.section

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.case_study_global_news.databinding.ActivitySectionBinding
import com.example.case_study_global_news.ui.BundleKeys


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
    }



}