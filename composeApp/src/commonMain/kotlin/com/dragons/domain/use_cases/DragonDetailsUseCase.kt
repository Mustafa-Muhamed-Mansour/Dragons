package com.dragons.domain.use_cases

import com.dragons.domain.repository.DragonDetailsRepository
import com.dragons.domain.response.CharacterResponseById

class DragonDetailsUseCase(
    private val dragonDetailsRepository: DragonDetailsRepository
) {
    suspend operator fun invoke(id: String): Result<CharacterResponseById> {
        return try {
            val result = dragonDetailsRepository.getDragonDetails(id = id)
            Result.success(value = result)
        } catch (e: Exception) {
            Result.failure(exception = e)
        }
    }
}