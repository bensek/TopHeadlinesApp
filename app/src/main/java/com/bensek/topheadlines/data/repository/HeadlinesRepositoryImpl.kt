package com.bensek.topheadlines.data.repository

import com.bensek.topheadlines.data.dto.toArticle
import com.bensek.topheadlines.data.remote.HeadlinesApi
import com.bensek.topheadlines.domain.model.Article
import com.bensek.topheadlines.domain.repository.HeadlinesRepository
import com.bensek.topheadlines.utils.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException

class HeadlinesRepositoryImpl(
    private val api: HeadlinesApi,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
): HeadlinesRepository {

    override suspend fun getTopHeadlines(sourceId: String): Resource<List<Article>> {
        return withContext(dispatcher) {
             try {
                val response = api.fetchTopHeadlines(sourceId)
                if (response.isSuccessful) {
                    Resource.Success(response.body()?.articles?.map { it.toArticle() })
                } else {
                    Resource.Error(response.message())
                }
            } catch (e: IOException) {
                Resource.NetworkFailure()
            } catch (e: Exception) {
                Resource.Error(e.message ?: "Failure")
            }
        }
    }
}