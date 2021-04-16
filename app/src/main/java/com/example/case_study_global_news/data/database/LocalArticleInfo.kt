package com.example.case_study_global_news.data.database

import android.text.format.DateUtils
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.case_study_global_news.data.domain.ArticleInfo
import java.text.SimpleDateFormat
import java.util.*

@Entity(tableName = "article_infos")
data class LocalArticleInfo(
    @PrimaryKey
    val title: String,
    val imageURL: String?,
    val datePublished: String,
    var url: String,
    val source: String
) {
    fun toDomainArticleInfo(): ArticleInfo {
        return ArticleInfo(
            title = title,
            imageURL = imageURL,
            datePublished = datePublished,
            url = url,
            source = source
        )
    }
}