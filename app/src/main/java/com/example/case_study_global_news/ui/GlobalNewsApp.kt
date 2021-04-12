package com.example.case_study_global_news.ui

import android.app.Application

class GlobalNewsApp: Application() {
    lateinit var serviceLocator: ServiceLocator

    override fun onCreate() {
        super.onCreate()
        serviceLocator = ServiceLocator(applicationContext)
    }
}