package com.bensek.topheadlines.ui.home

import androidx.activity.compose.BackHandler
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.bensek.topheadlines.R
import com.bensek.topheadlines.domain.model.Article
import com.bensek.topheadlines.ui.detail.DetailScreen

@Composable
fun AdaptivePane(
    isExpandedScreen: Boolean,
    uiState: HomeUiState,
    modifier: Modifier,
    onItemClicked: (Article) -> Unit,
    goBackToHome: () -> Unit
) {
    when(isExpandedScreen) {
        false -> {
            OnePane(
                uiState = uiState,
                modifier = modifier,
                onItemClicked = onItemClicked,
                goBackToHome = goBackToHome
            )
        }
        true -> {
            TwoPane(
                uiState = uiState,
                modifier = modifier,
                onItemClicked = onItemClicked
            )
        }
    }
}

@Composable
fun OnePane(
    uiState: HomeUiState,
    modifier: Modifier,
    onItemClicked: (Article) -> Unit,
    goBackToHome: () -> Unit
) {
    if (!uiState.isArticleOpen) {
        HeadlineList(
            modifier = modifier,
            articlesList = uiState.articlesList,
            onItemClicked = onItemClicked,
            articleSelected = uiState.articleSelected
        )
    } else {
        if (uiState.articleSelected != null) {
            DetailScreen(
                modifier = modifier,
                article = uiState.articleSelected
            )
            BackHandler {
                goBackToHome()
            }
        }
    }
}

@Composable
fun TwoPane(
    uiState: HomeUiState,
    modifier: Modifier,
    onItemClicked: (Article) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxSize()
    ) {
        HeadlineList(
            articlesList = uiState.articlesList,
            articleSelected = uiState.articleSelected,
            modifier = modifier.width(334.dp),
            onItemClicked = onItemClicked
        )
        Crossfade(targetState = uiState.articleSelected, label = "") { post ->
            if (post != null) {
                DetailScreen(
                    article = post,
                    modifier = modifier
                )
            } else {
                Box(Modifier.fillMaxSize()) {
                    Text(
                        text = stringResource(R.string.select_a_headline),
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
    }
}

