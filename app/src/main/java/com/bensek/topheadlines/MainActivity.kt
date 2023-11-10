package com.bensek.topheadlines

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.bensek.topheadlines.ui.screens.home.HomeScreen
import com.bensek.topheadlines.ui.theme.TopHeadlinesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TopHeadlinesTheme {
                HomeScreen()
            }
        }
    }
}
