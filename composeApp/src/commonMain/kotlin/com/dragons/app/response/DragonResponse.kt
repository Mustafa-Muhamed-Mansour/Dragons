package com.dragons.app.response

import com.dragons.app.entity.DragonModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class DragonResponse(
    @SerialName("items")
    val dragonModels: List<DragonModel>,
    val links: Links
)