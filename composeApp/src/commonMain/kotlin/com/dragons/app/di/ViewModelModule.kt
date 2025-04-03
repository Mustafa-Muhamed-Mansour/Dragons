package com.dragons.app.di

import com.dragons.app.view_model.DragonDetailsViewModel
import com.dragons.app.view_model.DragonsViewModel
import org.koin.compose.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { DragonsViewModel(homeRepository = get() ) }
    viewModel { DragonDetailsViewModel(homeRepository = get() ) }
//    viewModel { com.dragons.ball.app.view_models.DragonsViewModel(homeRepository = get()) }
}