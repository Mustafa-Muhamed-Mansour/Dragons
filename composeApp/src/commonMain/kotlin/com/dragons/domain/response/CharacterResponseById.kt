package com.dragons.domain.response

import com.dragons.domain.entity.TransformationModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharacterResponseById(
    val originPlanet: OriginPlanet,
    @SerialName("transformations")
    val transformations: List<TransformationModel>
)