package com.example.case_study_global_news.data.domain

import android.text.format.DateUtils
import com.example.case_study_global_news.data.database.LocalArticleInfo
import java.text.SimpleDateFormat
import java.util.*

data class ArticleInfo(
    val title: String,
    val imageURL: String?,
    val datePublished: String,
    var url: String,
    val source: String
){
    fun getFormattedDate(): CharSequence? {
        val currentDate = Date()
        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
        dateFormat.timeZone = TimeZone.getTimeZone("UTC")
        val oldDate: Date = dateFormat.parse(datePublished)
        return DateUtils.getRelativeTimeSpanString(
            oldDate.time,
            currentDate.time,
            DateUtils.HOUR_IN_MILLIS
        )
    }

    fun toLocalArticleInfo(): LocalArticleInfo = LocalArticleInfo(
        title = title,
        imageURL = imageURL,
        datePublished = datePublished,
        url = url,
        source = source
    )
}
