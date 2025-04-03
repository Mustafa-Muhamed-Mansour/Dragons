package com.dragons.app.network

import com.dragons.app.response.CharacterResponseById
import com.dragons.app.response.DragonResponse
import com.dragons.app.utils.Constants.BASE_URL
import com.dragons.app.utils.Constants.LIMIT
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url

class ApiService(
    private val client: HttpClient
) {

//    https://dragonball-api.com/api/characters/?page=1&limit=10
//    https://dragonball-api.com/api/characters/3
//    https://dragonball-api.com/api/characters?gender=male

    suspend fun getDragons(page: String): DragonResponse {
        return client.get {
            url(urlString = BASE_URL)
            parameter(key = "page", value = page)
            parameter(key = "limit", value = LIMIT)
        }.body<DragonResponse>()
    }

    suspend fun getDragonDetails(id: String): CharacterResponseById {
        return client.get {
            url(urlString = "$BASE_URL/$id")
        }.body<CharacterResponseById>()
    }
}