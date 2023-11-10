package com.bensek.topheadlines.ui.home

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
fun TwoPaneLayout(
    uiState: HomeUiState,
    contentModifier: Modifier,
    onItemClicked: (Article) -> Unit
) {

    Row(
        modifier = Modifier.fillMaxSize()
    ) {
        HeadlineList(
            articlesList = uiState.articlesList,
            articleSelected = uiState.articleSelected,
            modifier = contentModifier.width(334.dp),
            onItemClicked = onItemClicked
        )
        Crossfade(targetState = uiState.articleSelected, label = "") { post ->
            if (post != null) {
                DetailScreen(
                    article = post,
                    modifier = contentModifier
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