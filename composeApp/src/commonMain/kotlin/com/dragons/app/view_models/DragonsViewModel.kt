package com.dragons.app.view_models

import androidx.lifecycle.viewModelScope
import com.dragons.app.utils.UiStateDragons
import com.dragons.domain.use_cases.DragonsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent


class DragonsViewModel(
    private val dragonsUseCase: DragonsUseCase
) : androidx.lifecycle.ViewModel(), KoinComponent {
    private val _uiState = MutableStateFlow(value = UiStateDragons())
    val uiState = _uiState.asStateFlow()

    init {
        fetchDragons()
    }

    private fun fetchDragons() = viewModelScope.launch {
        _uiState.update { UiStateDragons(isLoading = true) }
        val response = dragonsUseCase()
        if (response.isSuccess) {
            _uiState.update { UiStateDragons(data = response.getOrThrow()) }
        } else {
            _uiState.update { UiStateDragons(error = response?.exceptionOrNull()?.message.toString()) }
        }
    }
}