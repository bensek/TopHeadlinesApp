package com.bensek.topheadlines.data.dto

data class TopHeadlineDto(
    val status: String,
    val totalResults: Int,
    val articles: List<ArticleDto>
)
