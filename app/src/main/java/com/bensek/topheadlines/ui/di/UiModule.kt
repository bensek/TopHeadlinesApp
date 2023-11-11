package com.bensek.topheadlines.ui.di

import com.bensek.topheadlines.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiModule = module {

    viewModel { HomeViewModel(get(), get(),get()) }

}