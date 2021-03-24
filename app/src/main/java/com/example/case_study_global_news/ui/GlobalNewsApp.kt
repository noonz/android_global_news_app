package com.example.case_study_global_news.ui

import android.app.Application

class GlobalNewsApp: Application() {
    val serviceLocator: ServiceLocator = ServiceLocator()
}