package com.dragons.app.di

import com.dragons.app.network.ApiService
import com.dragons.app.network.KtorClient
import org.koin.dsl.module

val apiModule = module {
    single { ApiService(client = KtorClient.httpClient) }
}