package com.example.case_study_global_news.data.network.models

import android.os.Build
import android.text.format.DateUtils
import android.util.Log
import androidx.annotation.RequiresApi
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.*

@JsonClass(generateAdapter = true)
data class Articles (
    var articles: List<ArticleInfo>
)

@JsonClass(generateAdapter = true)
data class ArticleInfo (
    var source: Source,
    var title: String,
    var url: String,

    @Json(name="urlToImage")
    var imageURL: String?,

    @Json(name="publishedAt")
    var datePublished: String
){
    fun getFormattedDate(): CharSequence? {


        val currentDate = Date()

        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'",Locale.getDefault())
        dateFormat.timeZone = TimeZone.getTimeZone("UTC")

        val oldDate: Date = dateFormat.parse(datePublished)

        return DateUtils.getRelativeTimeSpanString(oldDate.time,currentDate.time,DateUtils.HOUR_IN_MILLIS)

    }
    // TODO: convert date published to time since published. Ex: "published 2 hours ago"
}

@JsonClass(generateAdapter = true)
data class Source (
    @Json(name="name")
    var publisher: String
)

// DateTime.now()
// Example date
// 2021-03-19T18:33:08