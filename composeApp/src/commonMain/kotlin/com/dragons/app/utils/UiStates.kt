package com.dragons.app.utils

import com.dragons.app.entity.DragonModel
import com.dragons.app.entity.TransformationModel
import com.dragons.app.response.CharacterResponseById


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