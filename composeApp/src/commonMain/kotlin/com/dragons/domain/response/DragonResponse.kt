package com.dragons.domain.response

import com.dragons.domain.entity.DragonModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class DragonResponse(
    @SerialName("items")
    val dragonModels: List<DragonModel>
)