package com.bensek.topheadlines.domain.di

import com.bensek.topheadlines.domain.use_case.GetTopHeadlinesUseCase
import org.koin.dsl.module

val domainModule = module {

    single { GetTopHeadlinesUseCase(get()) }

}