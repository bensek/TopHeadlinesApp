package com.bensek.topheadlines.ui.home

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bensek.topheadlines.domain.model.Article
import com.bensek.topheadlines.domain.repository.HeadlinesRepository
import com.bensek.topheadlines.domain.repository.SourcesRepository
import com.bensek.topheadlines.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val sourcesRepository: SourcesRepository,
    private val headlinesRepository: HeadlinesRepository
): ViewModel() {

    private var _uiState: MutableStateFlow<HomeUiState> = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        initializeNewsSource()
    }

    fun initializeNewsSource() {
        viewModelScope.launch {
            sourcesRepository.getCurrentSource().collect { source ->
                updateSourceName(source.name)
                source.id?.let {
                    getTopHeadlines(it)
                }
            }
        }
    }

    private fun updateSourceName(name: String) {
        _uiState.update { it.copy(sourceName = name) }
    }

    private suspend fun getTopHeadlines(sourceId: String) {
        _uiState.update { it.copy(isLoading = true, hasError = false) }

        when(val result = headlinesRepository.getTopHeadlines(sourceId)) {
            is Resource.Error -> {
                _uiState.update {
                    it.copy(
                        hasError = true,
                        errorMessage = result.message,
                        isLoading = false
                    )
                }
            }
            is Resource.Success -> {
                result.data?.let { articles ->
                    _uiState.update {
                        it.copy(
                            articlesList = sortArticlesByLatest(articles),
                            isLoading = false,
                            hasError = false
                        )
                    }
                }
            }
        }
    }

    private fun sortArticlesByLatest(articles: List<Article>): List<Article> {
        return articles.sortedByDescending { it.dateTime }
    }

    fun onArticleSelected(article: Article) {
        _uiState.update {
            it.copy(articleSelected = article, isArticleOpen = true)
        }
    }

    fun goBackToArticleList() {
        _uiState.update {
            it.copy(articleSelected = null, isArticleOpen = false)
        }
    }
}