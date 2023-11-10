package com.bensek.topheadlines.domain.repository

import com.bensek.topheadlines.data.dto.TopHeadlineDto

interface HeadlinesRepository {
    suspend fun getTopHeadlines()
}

