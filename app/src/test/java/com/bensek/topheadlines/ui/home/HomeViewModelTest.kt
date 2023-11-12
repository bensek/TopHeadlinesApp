package com.bensek.topheadlines.ui.home

import androidx.lifecycle.SavedStateHandle
import com.bensek.topheadlines.domain.model.Article
import com.bensek.topheadlines.domain.model.Source
import com.bensek.topheadlines.domain.repository.HeadlinesRepository
import com.bensek.topheadlines.domain.repository.SourcesRepository
import com.bensek.topheadlines.utils.ReplaceMainDispatcherRule
import com.bensek.topheadlines.utils.Resource
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals

import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeViewModelTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    @get: Rule
    val replaceMainDispatcherRule = ReplaceMainDispatcherRule()

    private lateinit var viewModel: HomeViewModel
    private lateinit var sourcesRepository: SourcesRepository
    private lateinit var headlinesRepository: HeadlinesRepository
    private lateinit var savedStateHandle: SavedStateHandle

    @Before
    fun setup() {
        savedStateHandle = SavedStateHandle()
        sourcesRepository = mockk()
        headlinesRepository = mockk()
        viewModel = HomeViewModel(savedStateHandle, sourcesRepository, headlinesRepository)
    }

    @Test
    fun should_returnData_when_sourceExists() = runTest {
        val fakeSource = Source("bbc-news", "BBC News")
        every { sourcesRepository.getCurrentSource() } returns fakeSource
        coEvery { headlinesRepository.getTopHeadlines(any()) } returns Resource.Success(emptyList())

        viewModel.initializeNewsSource()

        assertEquals(fakeSource.name, viewModel.uiState.value.sourceName)
        assertEquals(emptyList<Article>(), viewModel.uiState.value.articlesList)
        assertEquals(false, viewModel.uiState.value.isLoading)
        assertEquals(false, viewModel.uiState.value.hasError)
        assertEquals(null, viewModel.uiState.value.errorMessage)
        assertEquals(null, viewModel.uiState.value.articleSelected)
        assertEquals(false, viewModel.uiState.value.isArticleOpen)
    }

    @Test
    fun should_returnList_when_ApiSuccess() = runTest {
        val fakeSource = Source("bbc-news", "BBC News")
        val fakeArticles = listOf(dummyArticle())
        every { sourcesRepository.getCurrentSource() } returns fakeSource
        coEvery { headlinesRepository.getTopHeadlines(any()) } returns Resource.Success(fakeArticles)

        viewModel.initializeNewsSource()

        assertEquals(fakeArticles, viewModel.uiState.value.articlesList)
        assertEquals(false, viewModel.uiState.value.isLoading)
        assertEquals(false, viewModel.uiState.value.hasError)
        assertEquals(null, viewModel.uiState.value.errorMessage)
        assertEquals(null, viewModel.uiState.value.articleSelected)
        assertEquals(false, viewModel.uiState.value.isArticleOpen)
    }

    @Test
    fun should_returnErrorMsg_when_ApiError() = runTest {
        val fakeSource = Source("bbc-news", "BBC News")
        val errorMsg = "Error Occurred"
        every { sourcesRepository.getCurrentSource() } returns fakeSource
        coEvery { headlinesRepository.getTopHeadlines(any()) } returns Resource.Error(errorMsg)

        viewModel.initializeNewsSource()

        assertEquals(false, viewModel.uiState.value.isLoading)
        assertEquals(true, viewModel.uiState.value.hasError)
        assertEquals(errorMsg, viewModel.uiState.value.errorMessage)
        assertEquals(emptyList<Article>(), viewModel.uiState.value.articlesList)
    }

    @Test
    fun should_returnTrue_when_articleClicked() {
        val fakeArticle = dummyArticle()

        viewModel.onArticleSelected(fakeArticle)

        assertEquals(fakeArticle, viewModel.uiState.value.articleSelected)
        assertEquals(true, viewModel.uiState.value.isArticleOpen)
    }

    @Test
    fun should_returnTrue_when_goBackToList() {
        viewModel.goBackToArticleList()

        assertEquals(null, viewModel.uiState.value.articleSelected)
        assertEquals(false, viewModel.uiState.value.isArticleOpen)
    }

    private fun dummyArticle() = Article("Title", "Description","Image")
}