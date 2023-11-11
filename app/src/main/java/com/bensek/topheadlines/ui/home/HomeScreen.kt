package com.bensek.topheadlines.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.bensek.topheadlines.R
import com.bensek.topheadlines.domain.model.Article
import com.bensek.topheadlines.ui.theme.LocalAppDimens
import org.koin.compose.koinInject

@Composable
fun HomeScreen(
    isExpandedScreen: Boolean = false,
    viewModel: HomeViewModel = koinInject()
) {
    val uiState by viewModel.uiState.collectAsState()

    when {
        uiState.isLoading -> {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }
        else -> {
            Scaffold(
                topBar = {
                    HomeTopBar(uiState.sourceName)
                }
            ) { innerPadding ->
                AdaptivePane(
                    isExpandedScreen = isExpandedScreen,
                    modifier = Modifier.padding(innerPadding),
                    uiState = uiState,
                    onItemClicked = {
                        viewModel.onArticleSelected(it)
                    },
                    goBackToHome = {
                        viewModel.goBackToArticleList()
                    }
                )
            }
        }
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
fun HeadlineList(
    modifier: Modifier = Modifier,
    articlesList: List<Article>,
    onItemClicked: (Article) -> Unit,
    articleSelected: Article?
) {
    val dimens = LocalAppDimens.current
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(dimens.spacingLarge)
    ) {
        items(articlesList) { article ->
            HeadlineListItem(
                article = article,
                onItemClicked = onItemClicked,
                isSelected = articleSelected?.title == article.title
            )
            Spacer(Modifier.height(dimens.spacingLarge))
        }
    }
}

@Composable
fun HeadlineListItem(
    article: Article,
    onItemClicked: (Article) -> Unit,
    isSelected: Boolean = false
) {
    val dimens = LocalAppDimens.current
    val cardColors = CardDefaults.cardColors(
        containerColor = if (!isSelected) {
            MaterialTheme.colorScheme.background
        } else {
            MaterialTheme.colorScheme.surfaceVariant
        }
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClicked(article) },
        shape = RoundedCornerShape(dimens.cornerRadius),
        colors = cardColors,
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimens.spacingLarge)
        ) {
            AsyncImage(
                model = article.imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .width(dimens.imageHeightSmall)
                    .clip(RoundedCornerShape(dimens.cornerRadius)),
                contentScale = ContentScale.Crop,
                placeholder = painterResource(id = R.drawable.placeholder_image)
            )
            Spacer(Modifier.height(dimens.spacingSmall))
            Text(
                text = article.title,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}