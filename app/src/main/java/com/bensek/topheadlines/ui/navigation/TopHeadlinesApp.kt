package com.bensek.topheadlines.ui.navigation

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.bensek.topheadlines.ui.theme.TopHeadlinesTheme

@Composable
fun TopHeadlinesApp(
    windowSizeClass: WindowSizeClass
) {
    val widthSizeClass = windowSizeClass.widthSizeClass
    val heightSizeClass = windowSizeClass.heightSizeClass

    val isExpandedScreen = widthSizeClass == WindowWidthSizeClass.Expanded

    TopHeadlinesTheme(
        widthSizeClass = widthSizeClass,
        heightSizeClass = heightSizeClass
    ) {
        val navController = rememberNavController()
        TopHeadlinesNavGraph(
            navController = navController,
            isExpandedScreen = isExpandedScreen
        )
    }
}