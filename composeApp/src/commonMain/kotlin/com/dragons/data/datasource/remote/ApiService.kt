package com.dragons.data.datasource.remote

import com.dragons.app.utils.Constants.BASE_URL
import com.dragons.app.utils.Constants.LIMIT
import com.dragons.app.utils.Constants.PAGE
import com.dragons.domain.response.CharacterResponseById
import com.dragons.domain.response.DragonResponse
import com.dragons.domain.response.SearchDragonsResponseItem
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url

class ApiService(
    private val client: HttpClient
) {
    suspend fun getDragons(): DragonResponse {
        return client.get {
            url(urlString = BASE_URL)
            parameter(key = "page", value = PAGE)
            parameter(key = "limit", value = LIMIT)
        }.body<DragonResponse>()
    }

    suspend fun getDragonDetails(id: String): CharacterResponseById {
        return client.get {
            url(urlString = "$BASE_URL/$id")
        }.body<CharacterResponseById>()
    }

    suspend fun getSearchDragons(searchName: String): List<SearchDragonsResponseItem> {
        return client.get {
            url(urlString = BASE_URL)
            parameter(key = "name", value = searchName)
        }.body<List<SearchDragonsResponseItem>>()
    }
}