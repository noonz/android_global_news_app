package com.example.case_study_global_news.data.network.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

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