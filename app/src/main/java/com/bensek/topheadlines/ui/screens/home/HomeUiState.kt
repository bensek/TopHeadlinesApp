package com.bensek.topheadlines.ui.screens.home

import com.bensek.topheadlines.domain.model.Article

data class HomeUiState(
    val articlesList: List<Article> = emptyList(),
    val isLoading: Boolean = true,
    val hasError: Boolean = false,
    val noInternet: Boolean = false,
    val sourceName: String = ""
)
