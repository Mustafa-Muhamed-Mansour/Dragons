package com.dragons.app.di

import com.dragons.app.view_model.DragonDetailsViewModel
import com.dragons.app.view_model.DragonsViewModel
import com.dragons.app.view_model.SearchDragonsViewModel
import org.koin.compose.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { DragonsViewModel(dragonsRepository = get() ) }
    viewModel { DragonDetailsViewModel(dragonDetailsRepository = get() ) }
    viewModel { SearchDragonsViewModel(searchDragonsRepository = get() ) }
}