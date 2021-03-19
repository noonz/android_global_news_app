package com.example.case_study_global_news.ui.section

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.case_study_global_news.R
import com.example.case_study_global_news.databinding.ActivityNewsDetailsBinding
import com.example.case_study_global_news.databinding.ActivitySearchBinding
import com.example.case_study_global_news.databinding.ActivitySectionBinding

class SectionActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySectionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySectionBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}