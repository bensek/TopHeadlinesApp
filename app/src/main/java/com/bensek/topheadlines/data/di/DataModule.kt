package com.bensek.topheadlines.data.di

import com.bensek.topheadlines.utils.Constants
import com.bensek.topheadlines.data.remote.ApiKeyInterceptor
import com.bensek.topheadlines.data.remote.HeadlinesApi
import com.bensek.topheadlines.data.repository.HeadlinesRepositoryImpl
import com.bensek.topheadlines.data.repository.SourcesRepositoryImpl
import com.bensek.topheadlines.domain.repository.HeadlinesRepository
import com.bensek.topheadlines.domain.repository.SourcesRepository
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module {

    single<OkHttpClient> {
        OkHttpClient.Builder()
            .addInterceptor(ApiKeyInterceptor())
            .build()
    }

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
    }

    single<HeadlinesApi> {
        get<Retrofit>()
            .create(HeadlinesApi::class.java)
    }

    single<HeadlinesRepository> {
        HeadlinesRepositoryImpl(get())
    }

    single<SourcesRepository> {
        SourcesRepositoryImpl()
    }
}