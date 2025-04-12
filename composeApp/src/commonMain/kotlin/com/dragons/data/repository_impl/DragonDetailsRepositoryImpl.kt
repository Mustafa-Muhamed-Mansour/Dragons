package com.dragons.data.repository_impl

import com.dragons.data.datasource.remote.ApiService
import com.dragons.domain.repository.DragonDetailsRepository
import com.dragons.domain.response.CharacterResponseById
import org.koin.core.component.KoinComponent

class DragonDetailsRepositoryImpl(
    private val apiService: ApiService
) : KoinComponent, DragonDetailsRepository {
    override suspend fun getDragonDetails(id: String): CharacterResponseById {
        return apiService.getDragonDetails(id = id)
    }

}