package com.dragons.app.repository

import com.dragons.app.entity.DragonModel
import com.dragons.app.network.ApiService
import org.koin.core.component.KoinComponent

class DragonsRepository(
    private val apiService: ApiService
) : KoinComponent {
    private suspend fun getDragons(): List<DragonModel> {
        return apiService.getDragons().dragonModels
    }

    suspend fun getAllDragons(): Result<List<DragonModel>> {
        return try {
            val result = this.getDragons()
            Result.success(value = result)
        } catch (e: Exception) {
            Result.failure(exception = e)
        }
    }
}