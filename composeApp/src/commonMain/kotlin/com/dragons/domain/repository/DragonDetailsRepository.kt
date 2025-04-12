package com.dragons.domain.repository

import com.dragons.domain.response.CharacterResponseById

interface DragonDetailsRepository {
    suspend fun getDragonDetails(id: String): CharacterResponseById
}