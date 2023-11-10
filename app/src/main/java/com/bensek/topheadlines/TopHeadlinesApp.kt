package com.bensek.topheadlines

import android.app.Application
import com.bensek.topheadlines.data.di.dataModule
import com.bensek.topheadlines.ui.di.uiModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class TopHeadlinesApp: Application() {

    override fun onCreate() {
        super.onCreate()
        initializeKoin()
    }

    private fun initializeKoin() {
        startKoin {
            androidLogger()
            androidContext(this@TopHeadlinesApp)
            modules(dataModule, uiModule)
        }
    }
}