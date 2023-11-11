package com.bensek.topheadlines.data.dto

import com.bensek.topheadlines.domain.model.Article

data class ArticleDto(
    val source: SourceDto,
    val author: String?,
    val title: String,
    val description: String?,
    val url: String,
    val urlToImage: String?,
    val publishedAt: String,
    val content: String?
)

fun ArticleDto.toArticle(): Article {
    return Article(
        title = title,
        description = description,
        imageUrl = urlToImage,
        content = content
    )
}
