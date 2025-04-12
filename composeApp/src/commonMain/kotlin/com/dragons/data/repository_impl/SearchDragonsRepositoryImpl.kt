package com.dragons.data.repository_impl

import com.dragons.data.datasource.remote.ApiService
import com.dragons.domain.repository.SearchDragonsRepository
import com.dragons.domain.response.SearchDragonsResponseItem
import org.koin.core.component.KoinComponent

class SearchDragonsRepositoryImpl(
    private val apiService: ApiService
) : KoinComponent, SearchDragonsRepository {
    override suspend fun getSearchDragon(searchName: String): List<SearchDragonsResponseItem> {
        return apiService.getSearchDragons(searchName = searchName)
    }
}