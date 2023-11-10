package com.bensek.topheadlines.data.remote

import com.bensek.topheadlines.data.dto.TopHeadlineDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface HeadlinesApi {

    @GET("top-headlines")
    suspend fun fetchTopHeadlines(@Query("sources") sources: String): Response<TopHeadlineDto>

}