package com.bensek.topheadlines.domain.repository

import com.bensek.topheadlines.domain.model.Source
import kotlinx.coroutines.flow.Flow

interface SourcesRepository {
    fun getCurrentSource(): Flow<Source>
}