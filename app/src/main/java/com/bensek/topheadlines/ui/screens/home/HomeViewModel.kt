package com.bensek.topheadlines.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bensek.topheadlines.domain.repository.HeadlinesRepository
import com.bensek.topheadlines.domain.repository.SourcesRepository
import com.bensek.topheadlines.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val sourcesRepository: SourcesRepository,
    private val headlinesRepository: HeadlinesRepository
): ViewModel() {

    private var _uiState: MutableStateFlow<HomeUiState> = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        initializeNewsSource()
    }

    private fun initializeNewsSource() {
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

            when(val result = headlinesRepository.getTopHeadlines(sourceId)) {
                is Resource.Error -> {
                    _uiState.update {
                        it.copy(hasError = true)
                    }
                }
                is Resource.NetworkFailure -> {
                    _uiState.update {
                        it.copy(noInternet = true)
                    }
                }
                is Resource.Success -> {
                    result.data?.let { articles ->
                        _uiState.update {
                            it.copy(
                                articlesList = articles,
                                isLoading = false,
                                hasError = false,
                                noInternet = false
                            )
                        }
                    }
                }
            }
    }
}