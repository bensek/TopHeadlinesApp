package com.bensek.topheadlines.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.bensek.topheadlines.R
import com.bensek.topheadlines.domain.model.Article
import com.bensek.topheadlines.ui.theme.LocalAppDimens

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
            .fillMaxWidth(),
        shape = RoundedCornerShape(dimens.cornerRadius),
        colors = cardColors,
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onItemClicked(article) }
                .padding(dimens.spacingLarge),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = article.imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .height(dimens.imageSmall)
                    .width(dimens.imageSmall)
                    .clip(RoundedCornerShape(dimens.cornerRadius)),
                contentScale = ContentScale.Crop,
                placeholder = painterResource(R.drawable.placeholder_image)
            )
            Spacer(Modifier.width(dimens.spacingLarge))
            Text(
                text = article.title,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground,
                maxLines = 4,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}