package com.bensek.topheadlines.ui.theme

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Dimens(
    val imageSmall: Dp = 0.dp,
    val imageLarge: Dp = 0.dp,
    val spacingSmall: Dp = 0.dp,
    val spacingLarge: Dp = 0.dp,
    val cornerRadius: Dp = 12.dp
)

val CompactDimens = Dimens(
    imageSmall = 110.dp,
    imageLarge = 160.dp,
    spacingSmall = 8.dp,
    spacingLarge = 16.dp
)

val MediumDimens = Dimens(
    imageSmall = 160.dp,
    imageLarge = 300.dp,
    spacingSmall = 16.dp,
    spacingLarge = 24.dp
)

val ExpandedDimens = Dimens(
    imageSmall = 80.dp,
    imageLarge = 250.dp,
    spacingSmall = 16.dp,
    spacingLarge = 24.dp
)