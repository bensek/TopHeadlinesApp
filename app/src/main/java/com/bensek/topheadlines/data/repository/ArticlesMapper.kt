package com.bensek.topheadlines.data.repository

import com.bensek.topheadlines.data.dto.ArticleDto
import com.bensek.topheadlines.domain.model.Article
import com.bensek.topheadlines.utils.convertToLocalDateTime
import com.bensek.topheadlines.utils.formatDateTime


class ArticlesMapper: Function1<List<ArticleDto>, List<Article>> {
    override fun invoke(movieDtos: List<ArticleDto>): List<Article> {
        return movieDtos
            .map { dto ->
                dto.toArticle()
            }
    }
}

fun ArticleDto.toArticle(): Article {
    return Article(
        title = title,
        description = description,
        imageUrl = urlToImage,
        content = content,
        dateTime = convertToLocalDateTime(publishedAt),
        displayDateTime = formatDateTime(publishedAt)
    )
}
