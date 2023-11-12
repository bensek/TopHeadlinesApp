package com.bensek.topheadlines.domain.repository

import com.bensek.topheadlines.domain.model.Source

interface SourcesRepository {
    fun getCurrentSource(): Source
}