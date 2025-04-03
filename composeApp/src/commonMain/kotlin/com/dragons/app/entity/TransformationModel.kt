package com.dragons.app.entity

import kotlinx.serialization.Serializable

@Serializable
data class TransformationModel(
    val image: String,
    val name: String
)