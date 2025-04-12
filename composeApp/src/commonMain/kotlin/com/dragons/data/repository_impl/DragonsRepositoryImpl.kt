package com.dragons.data.repository_impl

import com.dragons.data.datasource.remote.ApiService
import com.dragons.domain.entity.DragonModel
import com.dragons.domain.repository.DragonsRepository
import org.koin.core.component.KoinComponent

class DragonsRepositoryImpl(
    private val apiService: ApiService
) : KoinComponent, DragonsRepository {
    override suspend fun getDragons(): List<DragonModel> {
        return apiService.getDragons().dragonModels
    }
}