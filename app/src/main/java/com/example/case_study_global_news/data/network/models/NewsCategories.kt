package com.example.case_study_global_news.data.network.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NewsCategories (
    var sources: List<Categories>
)

@JsonClass(generateAdapter = true)
data class Categories (
    @Json(name="category")
    var newsCategory: String,
    var description: String,
    var name: String,
    var url: String
)
