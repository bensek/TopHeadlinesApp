package com.bensek.topheadlines.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.bensek.topheadlines.ui.home.HomeScreen

@Composable
fun TopHeadlinesNavGraph(
    navController: NavHostController,
    isExpandedScreen: Boolean
) {
    NavHost(
        navController = navController,
        startDestination = Screens.home
    ) {
        composable(Screens.home) {
            HomeScreen { articleClicked ->
                navController.navigate(Screens.detail)
            }
        }

        composable(Screens.detail) {
            //DetailScreen()
        }
    }

}