package com.bensek.topheadlines.ui.home

import com.bensek.topheadlines.domain.model.Article

data class HomeUiState(
    val articlesList: List<Article> = emptyList(),
    val isLoading: Boolean = true,
    val hasError: Boolean = false,
    val errorMessage: String? = null,
    val sourceName: String = "",
    val articleSelected: Article? = null,
    val isArticleOpen: Boolean = false
)
