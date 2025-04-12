package com.dragons.domain.response

import kotlinx.serialization.Serializable

@Serializable
data class SearchDragonsResponseItem(
    val gender: String,
    val id: Int,
    val image: String,
    val ki: String,
    val maxKi: String,
    val name: String,
    val race: String
)