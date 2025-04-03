package com.dragons.app.di

import com.dragons.app.repository.HomeRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory { HomeRepository(apiService = get()) }
}