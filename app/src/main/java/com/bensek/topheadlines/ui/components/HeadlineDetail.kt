package com.bensek.topheadlines.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.bensek.topheadlines.R
import com.bensek.topheadlines.domain.model.Article
import com.bensek.topheadlines.ui.theme.LocalAppDimens

@Composable
fun HeadlineDetail(
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
                .height(LocalAppDimens.current.imageLarge)
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
        if (article.displayDateTime != null) {
            Text(
                text = stringResource(R.string.published_at, article.displayDateTime),
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onBackground,
            )
        }
        Spacer(Modifier.height(dimens.spacingSmall))
        Divider(Modifier.height(1.dp))
        Spacer(Modifier.height(dimens.spacingSmall))
        if (article.description != null) {
            Text(
                text = article.description,
                fontStyle = FontStyle.Italic,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onBackground,
            )
        }
        Spacer(Modifier.height(dimens.spacingSmall))
        if (article.content != null) {
            Text(
                text = article.content,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onBackground,
            )
        }
    }
}