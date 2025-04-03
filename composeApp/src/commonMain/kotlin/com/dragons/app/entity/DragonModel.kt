package com.dragons.app.entity

import com.dragons.app.response.Transformation
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
//    val transformations: List<Transformation>
)