package com.bensek.topheadlines.domain.use_case

import com.bensek.topheadlines.domain.model.Article
import com.bensek.topheadlines.domain.repository.HeadlinesRepository
import com.bensek.topheadlines.utils.Resource

class GetTopHeadlinesUseCase(
    private val repository: HeadlinesRepository
) {
    suspend operator fun invoke(): Resource<List<Article>> {
        return repository.getTopHeadlines()
    }
}