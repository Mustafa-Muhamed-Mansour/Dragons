package com.dragons.domain.response

import kotlinx.serialization.Serializable

@Serializable
data class OriginPlanet(
    val name: String,
    val description: String,
    val image: String
)