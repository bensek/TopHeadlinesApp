package com.bensek.topheadlines.domain.model

import java.time.LocalDateTime

data class Article(
    val title: String,
    val description: String? = null,
    val imageUrl: String?,
    val content: String? = null,
    val dateTime: LocalDateTime? = null,
    val displayDateTime: String? = null
)
