package com.dragons.app.di

import com.dragons.domain.use_cases.DragonDetailsUseCase
import com.dragons.domain.use_cases.DragonsUseCase
import com.dragons.domain.use_cases.SearchDragonsUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { DragonsUseCase(dragonsRepository = get()) }
    single { DragonDetailsUseCase(dragonDetailsRepository = get()) }
    single { SearchDragonsUseCase(searchDragonsRepository = get()) }
}