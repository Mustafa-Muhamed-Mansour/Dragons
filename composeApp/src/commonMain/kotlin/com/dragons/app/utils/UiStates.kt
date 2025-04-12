package com.dragons.app.utils

import com.dragons.domain.entity.DragonModel
import com.dragons.domain.response.CharacterResponseById
import com.dragons.domain.response.SearchDragonsResponseItem


data class UiStateDragons(
    val isLoading: Boolean = false,
    val error: String = "",
    val data: List<DragonModel>? = null
)


data class UiStateDragonDetails(
    val isLoading: Boolean = false,
    val error: String = "",
    val data: CharacterResponseById? = null
)


data class UiStateSearchDragons(
    val isLoading: Boolean = false,
    val error: String = "",
    val data: List<SearchDragonsResponseItem>? = null
)
