package com.bensek.topheadlines.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bensek.topheadlines.domain.model.Article
import com.bensek.topheadlines.domain.use_case.GetTopHeadlinesUseCase
import com.bensek.topheadlines.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getTopHeadlinesUseCase: GetTopHeadlinesUseCase
): ViewModel() {

    private var _articlesList: MutableStateFlow<List<Article>> = MutableStateFlow(emptyList())
    val articlesList: StateFlow<List<Article>> = _articlesList.asStateFlow()

    init {
        getTopHeadlines()
    }

    private fun getTopHeadlines() {
        viewModelScope.launch {
            when(val result = getTopHeadlinesUseCase()) {
                is Resource.Error -> TODO()
                is Resource.NetworkFailure -> TODO()
                is Resource.Success -> {
                    result.data?.let {
                        _articlesList.value = it
                    }
                }
            }
        }
    }
}