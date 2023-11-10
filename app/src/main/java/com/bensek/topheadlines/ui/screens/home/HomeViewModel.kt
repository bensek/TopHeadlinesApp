package com.bensek.topheadlines.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bensek.topheadlines.domain.use_case.GetTopHeadlinesUseCase
import com.bensek.topheadlines.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getTopHeadlinesUseCase: GetTopHeadlinesUseCase
): ViewModel() {

    private var _uiState: MutableStateFlow<HomeUiState> = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        getTopHeadlines()
    }

    private fun getTopHeadlines() {
        viewModelScope.launch {
            when(val result = getTopHeadlinesUseCase()) {
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
}