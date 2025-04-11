package com.dragons.app.network

import com.dragons.app.response.CharacterResponseById
import com.dragons.app.response.DragonResponse
import com.dragons.app.response.SearchDragonsResponseItem
import com.dragons.app.utils.Constants.BASE_URL
import com.dragons.app.utils.Constants.HOST
import com.dragons.app.utils.Constants.LIMIT
import com.dragons.app.utils.Constants.PAGE
import com.dragons.app.utils.Constants.PATH
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import io.ktor.http.URLProtocol
import io.ktor.http.path

class ApiService(
    private val client: HttpClient
) {
    suspend fun getDragons(): DragonResponse {
        return client.get {
            url {
                protocol = URLProtocol.HTTPS
                host = HOST
                path(PATH)
                parameter(key = "page", value = PAGE)
                parameter(key = "limit", value = LIMIT)
            }
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