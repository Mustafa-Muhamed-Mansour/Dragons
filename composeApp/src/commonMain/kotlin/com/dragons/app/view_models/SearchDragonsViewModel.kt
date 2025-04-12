package com.dragons.app.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dragons.app.utils.UiStateSearchDragons
import com.dragons.domain.use_cases.SearchDragonsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

class SearchDragonsViewModel(
    private val searchDragonsUseCase: SearchDragonsUseCase
) : ViewModel(), KoinComponent {

    private val _uiState = MutableStateFlow(value = UiStateSearchDragons())
    val uiState = _uiState.asStateFlow()
    private val _query = MutableStateFlow(value = "")

    init {
        viewModelScope.launch {
            _query
                .debounce(timeoutMillis = 1000)
                .filter { it.isNotEmpty() }
                .collectLatest {
                    getSearchDragons(searchName = it)
                }
        }
    }

    fun updateQuery(s: String) {
        _query.update { s }
    }

    fun getSearchDragons(searchName: String) = viewModelScope.launch {
        _uiState.update { UiStateSearchDragons(isLoading = true) }
        val response = searchDragonsUseCase(searchName = searchName)
        if (response.isSuccess) {
            _uiState.update { UiStateSearchDragons(data = response?.getOrThrow()) }
        } else {
            _uiState.update { UiStateSearchDragons(error = response?.exceptionOrNull()?.message.toString()) }
        }
    }
}