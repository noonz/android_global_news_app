package com.example.case_study_global_news.data.network.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Categories (
    @Json(name="sources")
    var categories: List<CategoryInfo>
)

@JsonClass(generateAdapter = true)
data class CategoryInfo (
    var category: String,
    var description: String,
    var name: String,
    var url: String
)
