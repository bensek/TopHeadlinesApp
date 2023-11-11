package com.bensek.topheadlines.ui.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import coil.compose.AsyncImage
import com.bensek.topheadlines.R
import com.bensek.topheadlines.domain.model.Article
import com.bensek.topheadlines.ui.theme.LocalAppDimens

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    article: Article
) {
    val scrollState = rememberScrollState()
    val dimens = LocalAppDimens.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(dimens.spacingLarge)
    ) {
        AsyncImage(
            model = article.imageUrl,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(LocalAppDimens.current.imageHeightLarge)
                .clip(RoundedCornerShape(dimens.cornerRadius)),
            contentScale = ContentScale.Crop,
            placeholder = painterResource(id = R.drawable.placeholder_image)
        )
        Spacer(Modifier.height(dimens.spacingLarge))
        Text(
            text = article.title,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground,
            fontWeight = FontWeight.Bold
        )

        Spacer(Modifier.height(dimens.spacingSmall))
        if (article.description != null) {
            Text(
                text = article.description,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onBackground,
            )
        }
    }
}
