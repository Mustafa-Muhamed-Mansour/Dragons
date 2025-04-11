package com.dragons.app.repository

import com.dragons.app.network.ApiService
import com.dragons.app.response.SearchDragonsResponseItem
import org.koin.core.component.KoinComponent

class SearchDragonsRepository(
    private val apiService: ApiService
) : KoinComponent {
    private suspend fun getSearchDragon(searchName: String): List<SearchDragonsResponseItem> {
        return apiService.getSearchDragons(searchName = searchName)
    }

    suspend fun getSearchDragons(searchName: String): Result<List<SearchDragonsResponseItem>> {
        return try {
            val result = this.getSearchDragon(searchName = searchName)
            Result.success(value = result)
        } catch (e: Exception) {
            Result.failure(exception = e)
        }
    }
}