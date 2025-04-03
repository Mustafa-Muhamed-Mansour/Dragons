package com.dragons.app.screens.search

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import com.dragons.app.view_model.DragonsViewModel
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

object SearchDragonsScreen: Screen {

    @OptIn(KoinExperimentalAPI::class)
    @Composable
    override fun Content() {
//        val viewModel = koinViewModel<DragonsViewModel>()
        SearchDragons()
    }
}