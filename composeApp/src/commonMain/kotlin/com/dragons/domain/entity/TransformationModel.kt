package com.dragons.domain.entity

import kotlinx.serialization.Serializable

@Serializable
data class TransformationModel(
    val image: String,
    val name: String
)