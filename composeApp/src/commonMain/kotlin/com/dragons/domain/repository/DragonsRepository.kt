package com.dragons.domain.repository

import com.dragons.domain.entity.DragonModel

interface DragonsRepository {
    suspend fun getDragons(): List<DragonModel>
}