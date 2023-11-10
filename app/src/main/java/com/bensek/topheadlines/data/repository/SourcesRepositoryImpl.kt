package com.bensek.topheadlines.data.repository

import com.bensek.topheadlines.data.dto.SourceDto
import com.bensek.topheadlines.data.dto.toSource
import com.bensek.topheadlines.domain.model.Source
import com.bensek.topheadlines.domain.repository.SourcesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SourcesRepositoryImpl: SourcesRepository {

    override fun getCurrentSource(): Flow<Source> {
        return flow { emit(getBBCNewsSource()) }
    }

     private fun getBBCNewsSource(): Source {
        return SourceDto(
            id = "bbc-news",
            name =  "BBC News",
            description= "Use BBC News for up-to-the-minute news, breaking news, video, audio and feature stories. BBC News provides trusted World and UK news as well as local and regional perspectives. Also entertainment, business, science, technology and health news.",
            url = "http://www.bbc.co.uk/news",
            category = "general",
            language = "en",
            country = "gb"
        ).toSource()
    }
}