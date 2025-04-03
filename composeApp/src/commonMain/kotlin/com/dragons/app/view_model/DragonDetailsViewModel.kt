package com.dragons.app.view_model

import androidx.lifecycle.viewModelScope
import com.dragons.app.repository.HomeRepository
import com.dragons.app.utils.UiStateDragonDetails
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent


class DragonDetailsViewModel(
    private val homeRepository: HomeRepository
) : androidx.lifecycle.ViewModel(), KoinComponent {

    private val _uiState = MutableStateFlow(value = UiStateDragonDetails())
    val uiState = _uiState.asStateFlow()

    private val _page = MutableStateFlow(value = "")
    private val _query = MutableStateFlow(value = "")

    init {
//        viewModelScope.launch {
//            _query.debounce(timeoutMillis = 2000)
//                .filter { it.isNotEmpty() }
//                .collectLatest { fetchDragons(page = _page.value) }
//        }
    }


    fun fetchDragonDetails(id: String) = viewModelScope.launch {
        val response = homeRepository.getAllDetailsDragon(id = id)
        if (response.isSuccess) {
            _uiState.update { UiStateDragonDetails(data = response.getOrThrow()) }
        } else {
            _uiState.update { UiStateDragonDetails(error = response?.exceptionOrNull()?.message.toString()) }
        }
    }
}

//    private val _listDragons: MutableState<MainStateDragons> = mutableStateOf(MainStateDragons())
//    val listDragons get() = _listDragons
////    private val _genderDragons: MutableState<MainStateDragons> = mutableStateOf(MainStateDragons())
//
//    private val _listTransformationDragons: MutableState<MainStateTransformationDragon> = mutableStateOf(MainStateTransformationDragon())
//    val listTransformationDragons get() = _listTransformationDragons
//
//    private val _error: MutableStateFlow<String> = MutableStateFlow("")
//    val error: StateFlow<String> get() = _error
//
//
//
//    init {
//        _listDragons.value = MainStateDragons(isLoading = true)
////        _listTransformationDragons.value = MainStateTransformationDragon(isLoading = true)
//    }
//
//    fun fetchAllDragons(page: Int) {
//        viewModelScope.launch {
//            try {
//                when (val products = homeRepository.fetchDragons(page = page, limit = LIMIT)) {
//                    is Resource.Error -> {
//                        _listDragons.value = MainStateDragons(error = "Something went wrong")
//                    }
//                    is Resource.Success -> {
//                        products.data?.let {
//                            _listDragons.value = MainStateDragons(data = it.dragonModels.toList())
//                        }
//                    }
//                    is Resource.Loading -> {
//                        _listDragons.value = MainStateDragons(isLoading = true)
//                    }
//                }
//            } catch (e: Exception) {
//                _error.value = e.message.toString()
//            }
//        }
//    }
//
//    fun fetchFilterDragons(gender: String) {
//        viewModelScope.launch {
//            try {
//                when (val gender = homeRepository.fetchFilterDragons(gender = gender)) {
//                    is Resource.Error -> {
//                        _listDragons.value = MainStateDragons(error = "Something went wrong")
//                    }
//                    is Resource.Success -> {
//                        gender.data?.let {
//                            _listDragons.value = MainStateDragons(data = it.dragonModels.toList())
//                        }
//                    }
//                    is Resource.Loading -> {
//                        _listDragons.value = MainStateDragons(isLoading = true)
//                    }
//                }
//            } catch (e: Exception) {
//                _error.value = e.message.toString()
//            }
//        }
//    }
//
//    fun fetchAllTransformationDragon(id: Int) {
//        viewModelScope.launch {
//            try {
//                when (val products = homeRepository.fetchTransformationDragon(id = id)) {
//                    is Resource.Error -> {
//                        _listTransformationDragons.value = MainStateTransformationDragon(error = "Something went wrong")
//                    }
//                    is Resource.Success -> {
//                        products.data?.let {
//                            _listTransformationDragons.value = MainStateTransformationDragon(data = it.transformations.toList())
//                        }
//                    }
//                    is Resource.Loading -> {
//                        _listTransformationDragons.value = MainStateTransformationDragon(isLoading = true)
//                    }
//                }
//            } catch (e: Exception) {
//                _error.value = e.message.toString()
//            }
//        }
//    }
//}