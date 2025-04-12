package com.dragons.domain.use_cases

import com.dragons.domain.repository.SearchDragonsRepository
import com.dragons.domain.response.SearchDragonsResponseItem

class SearchDragonsUseCase(
    private val searchDragonsRepository: SearchDragonsRepository
) {
    suspend operator fun invoke(searchName: String): Result<List<SearchDragonsResponseItem>> {
        return try {
            val result = searchDragonsRepository.getSearchDragon(searchName = searchName)
            Result.success(value = result)
        } catch (e: Exception) {
            Result.failure(exception = e)
        }
    }
}