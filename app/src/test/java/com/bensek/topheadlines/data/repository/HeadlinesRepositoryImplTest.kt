package com.bensek.topheadlines.data.repository

import com.bensek.topheadlines.data.dto.ArticleDto
import com.bensek.topheadlines.data.dto.SourceDto
import com.bensek.topheadlines.data.dto.TopHeadlineDto
import com.bensek.topheadlines.data.remote.HeadlinesApi
import com.bensek.topheadlines.domain.repository.HeadlinesRepository
import com.bensek.topheadlines.utils.ReplaceMainDispatcherRule
import com.google.gson.Gson
import org.junit.Assert.*

import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.SocketPolicy
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.HttpURLConnection

@RunWith(RobolectricTestRunner::class)
@ExperimentalCoroutinesApi
class HeadlinesRepositoryImplTest {

    @get: Rule
    val replaceMainDispatcherRule = ReplaceMainDispatcherRule()

    private lateinit var headlinesRepository: HeadlinesRepository
    private lateinit var mapper: ArticlesMapper
    private lateinit var headlinesApi: HeadlinesApi
    private lateinit var mockWebServer: MockWebServer

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        mockWebServer.start()
        headlinesApi = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/").toString())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(HeadlinesApi::class.java)
        mapper = ArticlesMapper()
        headlinesRepository = HeadlinesRepositoryImpl(headlinesApi, mapper, UnconfinedTestDispatcher())
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun should_returnArticles_when_ApiSuccessful () = runTest {
        val headlineDto = dummyApiResponseDto()
        val expectedList = mapper(headlineDto.articles)
        val expectedResponse = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(Gson().toJson(headlineDto))
        mockWebServer.enqueue(expectedResponse)

        val actualResponse = headlinesRepository.getTopHeadlines("123")

        assertEquals(expectedList, actualResponse.data)
        assertNull(actualResponse.message)
    }

    private fun dummyApiResponseDto(): TopHeadlineDto {
        val fakeSourceDto = SourceDto("123", "Test News", null, null, null, null, null)
        val articleDto = ArticleDto(fakeSourceDto, null, "title", null, "", null, "", null)
        val articles = listOf(articleDto, articleDto)
        return TopHeadlineDto(
            status = "200",
            totalResults = 2,
            articles = articles
        )
    }

    @Test
    fun should_returnError_when_ApiHasServerError () = runTest {
        val expectedResponse = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_INTERNAL_ERROR)
        mockWebServer.enqueue(expectedResponse)

        val actualResponse = headlinesRepository.getTopHeadlines("123")

        assertNotNull(actualResponse.message)
        assertNull(actualResponse.data)
    }

    @Test
    fun should_returnError_when_ApiThrowsNetworkException () = runTest {
        val expectedResponse = MockResponse()
            .setSocketPolicy(SocketPolicy.DISCONNECT_AT_START)
        mockWebServer.enqueue(expectedResponse)

        val actualResponse = headlinesRepository.getTopHeadlines("123")

        assertNotNull(actualResponse.message)
        assertNull(actualResponse.data)
    }
}
