package com.dragons.app.screens.search

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import com.dragons.app.view_model.SearchDragonsViewModel
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

object SearchDragonsScreen: Screen {
    private fun readResolve(): Any = SearchDragonsScreen
    @OptIn(KoinExperimentalAPI::class)
    @Composable
    override fun Content() {
        val viewModel = koinViewModel<SearchDragonsViewModel>()
        SearchDragons(searchDragonsViewModel = viewModel)
    }
}