package com.example.case_study_global_news.data.network.models

import android.text.format.DateUtils
import com.example.case_study_global_news.data.database.LocalArticleInfo
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.text.SimpleDateFormat
import java.util.*

@JsonClass(generateAdapter = true)
data class Articles (
    var articles: List<WebArticleInfo>
)

@JsonClass(generateAdapter = true)
data class WebArticleInfo (
    var source: Source,
    var title: String,
    var url: String,
    @Json(name="urlToImage")
    var imageURL: String?,

    @Json(name="publishedAt")
    var datePublished: String
){
    fun toLocalArticleInfo(): LocalArticleInfo{
        return LocalArticleInfo(
            title = title,
            imageURL = imageURL,
            datePublished = datePublished,
            url = url,
            source = source.publisher
        )
    }
}

@JsonClass(generateAdapter = true)
data class Source (
    @Json(name="name")
    var publisher: String
)
