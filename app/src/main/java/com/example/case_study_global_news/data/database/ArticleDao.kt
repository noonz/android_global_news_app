package com.example.case_study_global_news.data.database

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface ArticleDao {
    @Query("SELECT * FROM article_infos")
    fun getArticleInfos():LiveData<List<LocalArticleInfo>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(articleInfos:List<LocalArticleInfo>)

    @Update
    fun update(articleInfos: LocalArticleInfo)
}