package com.bensek.topheadlines.domain.model

data class Article(
    val title: String,
    val description: String? = null,
    val imageUrl: String?
)
