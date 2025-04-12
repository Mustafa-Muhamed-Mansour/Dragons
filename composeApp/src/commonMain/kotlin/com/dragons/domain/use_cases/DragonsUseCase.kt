package com.dragons.domain.use_cases

import com.dragons.domain.entity.DragonModel
import com.dragons.domain.repository.DragonsRepository
import com.dragons.domain.repository.SearchDragonsRepository

class DragonsUseCase(
    private val dragonsRepository: DragonsRepository
) {
    suspend operator fun invoke(): Result<List<DragonModel>> {
        return try {
            val result = dragonsRepository.getDragons()
            Result.success(value = result)
        } catch (e: Exception) {
            Result.failure(exception = e)
        }
    }

}