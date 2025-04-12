package com.dragons.app.di

import com.dragons.data.datasource.remote.ApiService
import com.dragons.data.datasource.remote.KtorClient
import org.koin.dsl.module

val apiModule = module {
    single { ApiService(client = KtorClient.httpClient) }
}