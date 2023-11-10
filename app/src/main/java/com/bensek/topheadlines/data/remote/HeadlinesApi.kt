package com.bensek.topheadlines.data.remote

import com.bensek.topheadlines.data.dto.TopHeadlineDto
import retrofit2.Response
import retrofit2.http.GET

interface HeadlinesApi {

    @GET("top-headlines?sources=bbc-news")
    suspend fun fetchTopHeadlines(): Response<TopHeadlineDto>

}