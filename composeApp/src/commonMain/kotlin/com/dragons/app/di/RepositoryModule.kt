package com.dragons.app.di

import com.dragons.data.repository_impl.DragonDetailsRepositoryImpl
import com.dragons.data.repository_impl.DragonsRepositoryImpl
import com.dragons.data.repository_impl.SearchDragonsRepositoryImpl
import com.dragons.domain.repository.DragonDetailsRepository
import com.dragons.domain.repository.DragonsRepository
import com.dragons.domain.repository.SearchDragonsRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<DragonsRepository> { DragonsRepositoryImpl(apiService = get()) }
    single<DragonDetailsRepository> { DragonDetailsRepositoryImpl(apiService = get()) }
    single<SearchDragonsRepository> { SearchDragonsRepositoryImpl(apiService = get()) }
}