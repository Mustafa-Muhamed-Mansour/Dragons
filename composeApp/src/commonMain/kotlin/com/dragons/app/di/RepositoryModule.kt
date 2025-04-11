package com.dragons.app.di

import com.dragons.app.repository.DragonDetailsRepository
import com.dragons.app.repository.DragonsRepository
import com.dragons.app.repository.SearchDragonsRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { DragonsRepository(apiService = get()) }
    single { DragonDetailsRepository(apiService = get()) }
    single { SearchDragonsRepository(apiService = get()) }
}