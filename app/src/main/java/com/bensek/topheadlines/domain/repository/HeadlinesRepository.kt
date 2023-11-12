package com.bensek.topheadlines.domain.repository

import com.bensek.topheadlines.domain.model.Article
import com.bensek.topheadlines.utils.Resource

interface HeadlinesRepository {
    suspend fun getTopHeadlines(sourceId: String): Resource<List<Article>>
}

