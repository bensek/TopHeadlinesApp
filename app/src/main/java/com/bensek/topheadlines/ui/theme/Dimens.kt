package com.bensek.topheadlines.ui.theme

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Dimens(
    val imageHeightSmall: Dp = 0.dp,
    val imageHeightLarge: Dp = 0.dp,
    val spacingSmall: Dp = 0.dp,
    val spacingLarge: Dp = 0.dp,
    val cornerRadius: Dp = 12.dp
)

val CompactDimens = Dimens(
    imageHeightSmall = 120.dp,
    imageHeightLarge = 200.dp,
    spacingSmall = 8.dp,
    spacingLarge = 16.dp
)

val MediumDimens = Dimens(
    imageHeightSmall = 160.dp,
    imageHeightLarge = 300.dp,
    spacingSmall = 16.dp,
    spacingLarge = 24.dp
)

val ExpandedDimens = Dimens(
    imageHeightSmall = 120.dp,
    imageHeightLarge = 200.dp,
    spacingSmall = 16.dp,
    spacingLarge = 24.dp
)