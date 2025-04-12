package com.dragons.domain.entity

import kotlinx.serialization.Serializable

@Serializable
data class DragonModel(
    val affiliation: String,
    val description: String,
    val gender: String,
    val id: Int,
    val image: String,
    val ki: String,
    val maxKi: String,
    val name: String,
    val race: String,
)
