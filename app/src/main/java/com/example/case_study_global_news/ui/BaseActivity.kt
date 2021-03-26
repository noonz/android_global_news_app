package com.example.case_study_global_news.ui

import android.app.ActivityOptions
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

        bottomNav.setOnNavigationItemSelectedListener { menuItem ->
            if (menuItem.itemId == selectedItemId){
                return@setOnNavigationItemSelectedListener true
            }
            val options = ActivityOptions.makeSceneTransitionAnimation(
                this, bottomNav,"bottom_nav_bar"
            )
            when (menuItem.itemId) {
                R.id.home -> {
                    startActivity(Intent(this, MainActivity::class.java), options.toBundle())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.search -> {
                    startActivity(Intent(this, SearchActivity::class.java), options.toBundle())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.categories -> {
                    startActivity(Intent(this, CategoryActivity::class.java), options.toBundle())
                    return@setOnNavigationItemSelectedListener true
                }
            }
            false
        }
    }

    override fun onResume() {
        super.onResume()
        bottomNav.selectedItemId = selectedItemId
    }


}