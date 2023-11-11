package com.bensek.topheadlines.ui.navigation

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.bensek.topheadlines.ui.theme.TopHeadlinesTheme

@Composable
fun TopHeadlinesApp(
    widthSizeClass: WindowWidthSizeClass
) {
    TopHeadlinesTheme(
        widthSizeClass = widthSizeClass
    ) {
        val navController = rememberNavController()
        val isExpandedScreen = widthSizeClass == WindowWidthSizeClass.Expanded
        TopHeadlinesNavGraph(
            navController = navController,
            isExpandedScreen = isExpandedScreen
        )
    }
}