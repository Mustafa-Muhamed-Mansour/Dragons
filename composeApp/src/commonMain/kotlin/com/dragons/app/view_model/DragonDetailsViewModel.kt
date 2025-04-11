package com.dragons.app.view_model

import androidx.lifecycle.viewModelScope
import com.dragons.app.repository.DragonDetailsRepository
import com.dragons.app.utils.UiStateDragonDetails
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent


class DragonDetailsViewModel(
    private val dragonDetailsRepository: DragonDetailsRepository
) : androidx.lifecycle.ViewModel(), KoinComponent {

    private val _uiState = MutableStateFlow(value = UiStateDragonDetails())
    val uiState = _uiState.asStateFlow()

    fun fetchDragonDetails(id: String) = viewModelScope.launch {
        val response = dragonDetailsRepository.getAllDetailsDragon(id = id)
        if (response.isSuccess) {
            _uiState.update { UiStateDragonDetails(data = response.getOrThrow()) }
        } else {
            _uiState.update { UiStateDragonDetails(error = response?.exceptionOrNull()?.message.toString()) }
        }
    }
}