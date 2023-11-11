package com.bensek.topheadlines.ui.navigation

import android.util.Log
import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.bensek.topheadlines.ui.theme.TopHeadlinesTheme
import com.bensek.topheadlines.utils.Constants

@Composable
fun TopHeadlinesApp(
    windowSizeClass: WindowSizeClass
) {
    val widthSizeClass = windowSizeClass.widthSizeClass
    val heightSizeClass = windowSizeClass.heightSizeClass
    Log.v(Constants.LOG_TAG, "MyWindowSizeClass: Width -> $widthSizeClass\nHeight -> $heightSizeClass")

    val isExpandedScreen = widthSizeClass == WindowWidthSizeClass.Expanded ||
            (widthSizeClass == WindowWidthSizeClass.Medium && heightSizeClass == WindowHeightSizeClass.Compact)

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