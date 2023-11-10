package com.bensek.topheadlines.ui.screens.home

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.bensek.topheadlines.domain.model.Article
import org.koin.compose.koinInject

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = koinInject()
) {
    val articlesList by viewModel.articlesList.collectAsState()

    Scaffold(
        topBar = {
            HomeTopBar()
        }
    ) { innerPadding ->

        HeadlineList(
            modifier = Modifier.padding(innerPadding),
            articlesList = articlesList
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeTopBar() {
    TopAppBar(title = {
        Text(text = "BBC News")
    })
}

@Composable
private fun HeadlineList(
    modifier: Modifier,
    articlesList: List<Article>
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(articlesList) { article ->
            Text(text = article.title )
        }
    }
}