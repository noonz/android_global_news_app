package com.example.case_study_global_news.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.case_study_global_news.R
import com.example.case_study_global_news.ui.categories.CategoryActivity
import com.example.case_study_global_news.ui.main.MainActivity
import com.example.case_study_global_news.ui.search.SearchActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

abstract class BaseActivity : AppCompatActivity() {
    abstract val bottomNav: BottomNavigationView
    abstract val selectedItemId: Int

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        bottomNav.setOnNavigationItemSelectedListener(navItemSelectedListener)
    }

    override fun onResume() {
        super.onResume()
        bottomNav.selectedItemId = selectedItemId
    }

    private val navItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
            if (menuItem.itemId == selectedItemId){
                return@OnNavigationItemSelectedListener true
            }
            when (menuItem.itemId) {
                R.id.home -> {
                    startActivity(Intent(this, MainActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.search -> {
                    startActivity(Intent(this, SearchActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.categories -> {
                    startActivity(Intent(this, CategoryActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }
}