package com.dragons.app.response

import com.dragons.app.entity.TransformationModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharacterResponseById(
    val originPlanet: OriginPlanet,
    @SerialName("transformations")
    val transformations: List<TransformationModel>
)