package com.bensek.topheadlines.data.repository

import android.util.Log
import com.bensek.topheadlines.core.Constants.LOG_TAG
import com.bensek.topheadlines.data.remote.HeadlinesApi
import com.bensek.topheadlines.domain.repository.HeadlinesRepository

class HeadlinesRepositoryImpl(
    private val api: HeadlinesApi
): HeadlinesRepository {
    override suspend fun getTopHeadlines() {
        try {
            val response = api.fetchTopHeadlines()
            if (response.isSuccessful) {
                Log.v(LOG_TAG, "Repository: Success -> ${response.body()?.articles?.size}")
            } else {
                Log.v(LOG_TAG, "Repository: Error -> ${response.errorBody()?.string()}")
            }
        } catch (e: Exception) {
            Log.v(LOG_TAG, "Repository: Failure -> ${e.message}")
        }
    }
}