package com.bensek.topheadlines.data.repository

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
    private val mapper: ArticlesMapper,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
): HeadlinesRepository {

    override suspend fun getTopHeadlines(sourceId: String): Resource<List<Article>> {
        return withContext(dispatcher) {
             try {
                val response = api.fetchTopHeadlines(sourceId)
                if (response.isSuccessful) {
                    if (response.body()?.articles != null) {
                        Resource.Success(mapper(response.body()?.articles!!))
                    } else {
                        Resource.Error("Articles list is null")
                    }
                } else {
                    Resource.Error(response.errorBody()?.string() ?: "An error occurred")
                }
            } catch (e: IOException) {
                Resource.Error("No internet connection. Please check your network settings and try again.")
            } catch (e: Exception) {
                Resource.Error(e.message ?: "Failure")
            }
        }
    }
}