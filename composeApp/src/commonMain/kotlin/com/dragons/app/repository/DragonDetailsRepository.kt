package com.dragons.app.repository

import com.dragons.app.network.ApiService
import com.dragons.app.response.CharacterResponseById
import org.koin.core.component.KoinComponent

class DragonDetailsRepository(
    private val apiService: ApiService
) : KoinComponent {
    private suspend fun getDragonDetails(id: String): CharacterResponseById {
        return apiService.getDragonDetails(id = id)
    }

    suspend fun getAllDetailsDragon(id: String): Result<CharacterResponseById> {
        return try {
            val result = this.getDragonDetails(id = id)
            Result.success(value = result)
        } catch (e: Exception) {
            Result.failure(exception = e)
        }
    }
}