package com.dragons.domain.repository

import com.dragons.domain.response.SearchDragonsResponseItem

interface SearchDragonsRepository {
    suspend fun getSearchDragon(searchName: String): List<SearchDragonsResponseItem>
}