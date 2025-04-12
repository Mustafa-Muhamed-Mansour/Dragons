package com.dragons.app.di

import com.dragons.app.view_models.DragonDetailsViewModel
import com.dragons.app.view_models.DragonsViewModel
import com.dragons.app.view_models.SearchDragonsViewModel
import org.koin.compose.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { DragonsViewModel(dragonsUseCase = get()) }
    viewModel { DragonDetailsViewModel(dragonDetailsUseCase = get()) }
    viewModel { SearchDragonsViewModel(searchDragonsUseCase = get()) }
}