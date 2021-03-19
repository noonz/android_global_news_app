package com.example.case_study_global_news.data.network.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NewsInfo (
    var articles: List<Articles>
)

@JsonClass(generateAdapter = true)
data class Articles (
    var source: Source,
    var title: String,
    var url: String,

    @Json(name="urlToImage")
    var imageURL: String?,

    @Json(name="publishedAt")
    var datePublished: String
)

@JsonClass(generateAdapter = true)
data class Source (

    @Json(name="name")
    var publisher: String
)