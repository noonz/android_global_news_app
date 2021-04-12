package com.example.case_study_global_news.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [LocalArticleInfo::class], version = 1)
abstract class NewsArticleDatabase:RoomDatabase(){
    abstract fun getArticleDao(): ArticleDao
}