package com.bensek.topheadlines.ui.home

import androidx.activity.compose.BackHandler
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.bensek.topheadlines.R
import com.bensek.topheadlines.domain.model.Article
import com.bensek.topheadlines.ui.components.HeadlineDetail
import com.bensek.topheadlines.ui.components.HeadlineList
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    isExpandedScreen: Boolean = false,
    viewModel: HomeViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    when {
        uiState.isLoading -> {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }
        uiState.hasError -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                uiState.errorMessage?.let { error ->
                    Text(text = error, modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.CenterHorizontally))
                    Button(onClick = { viewModel.initializeNewsSource() }) {
                        Text(text = stringResource(R.string.try_again))
                    }
                }
            }
        }

        else -> {
            HomeContent(
                uiState = uiState,
                isExpandedScreen = isExpandedScreen,
                onItemClicked = { viewModel.onArticleSelected(it) },
                navigateBack = { viewModel.goBackToArticleList() }
            )
        }
    }
}

@Composable
private fun HomeContent(
    uiState: HomeUiState,
    isExpandedScreen: Boolean,
    onItemClicked: (Article) -> Unit,
    navigateBack: () -> Unit
) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.surface,
        topBar = {
            HomeTopBar(uiState.sourceName)
        }
    ) { innerPadding ->
        AdaptivePane(
            isExpandedScreen = isExpandedScreen,
            modifier = Modifier.padding(innerPadding),
            uiState = uiState,
            onItemClicked = onItemClicked,
            goBackToHome = navigateBack
        )
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeTopBar(sourceName: String) {
    TopAppBar(
        title = {
        Text(text = sourceName)
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary
        )
    )
}

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
            articleSelected = uiState.articleSelected,
            isExpandedScreen = false
        )
    } else {
        Crossfade(targetState = uiState.articleSelected, label = "") { post ->
            if (post != null) {
                HeadlineDetail(
                    modifier = modifier,
                    article = post
                )
                BackHandler {
                    goBackToHome()
                }
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
            isExpandedScreen = true,
            modifier = modifier.width(350.dp),
            onItemClicked = onItemClicked
        )
        Crossfade(targetState = uiState.articleSelected, label = "") { post ->
            if (post != null) {
                HeadlineDetail(
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


