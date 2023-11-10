package com.bensek.topheadlines.ui.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bensek.topheadlines.ui.detail.DetailScreen
import com.bensek.topheadlines.ui.home.HomeScreen
import com.bensek.topheadlines.utils.Constants.LOG_TAG

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screens.home
    ) {
        composable(Screens.home) {
            HomeScreen { articleClicked ->
                Log.v(LOG_TAG, "ArticleClicked - $articleClicked")
                navController.navigate(Screens.detail)
            }
        }

        composable(Screens.detail) {
            DetailScreen()
        }
    }

}