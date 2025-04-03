package com.dragons.app.repository

import com.dragons.app.entity.DragonModel
import com.dragons.app.entity.TransformationModel
import com.dragons.app.network.ApiService
import com.dragons.app.response.CharacterResponseById
import org.koin.core.component.KoinComponent

class HomeRepository(
//    private val ktorClient: KtorClient
    private val apiService: ApiService
) : KoinComponent {
    private suspend fun getDragons(page: String): List<DragonModel> {
        return apiService.getDragons(page = page).dragonModels
    }

//    private suspend fun getDragonDetails(id: String): List<TransformationModel> {
//        return apiService.getDragonDetails(id = id).transformations
//    }
    private suspend fun getDragonDetails(id: String): CharacterResponseById {
        return apiService.getDragonDetails(id = id)
    }

    suspend fun getAllDragons(page: String): Result<List<DragonModel>> {
        return try {
            val result = this.getDragons(page = page)
            Result.success(value = result)
        } catch (e: Exception) {
            Result.failure(exception = e)
        }
    }

//    suspend fun getAllDetailsDragon(id: String): Result<List<TransformationModel>> {
//        return try {
//            val result = this.getDragonDetails(id = id)
//            Result.success(value = result)
//        } catch (e: Exception) {
//            Result.failure(exception = e)
//        }
//    }
    suspend fun getAllDetailsDragon(id: String): Result<CharacterResponseById> {
        return try {
            val result = this.getDragonDetails(id = id)
            Result.success(value = result)
        } catch (e: Exception) {
            Result.failure(exception = e)
        }
    }
}

//    private suspend fun httpClintDragons(page: Int, limit: Int): DragonResponse {
//        val response = ktorClient.httpClient.get(urlString = "${BASE_URL}?${"page=$page"}&${"limit=$limit"}")
//        return response.body()
//    }
//
//    private suspend fun httpClintTransformationDragon(id: Int): CharacterResponseById {
//        val response = ktorClient.httpClient.get(urlString = "${BASE_URL_TRANSFORMATION}/${"$id"}")
//        return response.body()
//    }
//
//    private suspend fun httpClintFilterDragon(gender: String): DragonResponse {
//        val response = ktorClient.httpClient.get(urlString = "${BASE_URL}?${"gender=$gender"}")
//        return response.body()
//    }
//
////    https://dragonball-api.com/api/characters?gender=male
//
//
//    suspend fun fetchDragons(page: Int, limit: Int): Resource<DragonResponse?> {
//        return try {
//            val result = httpClintDragons(page = page, limit = limit)
//            Resource.Success(data = result)
//        } catch (e: Exception) {
//            Resource.Error(e.message.toString())
//        }
//    }
//
//    suspend fun fetchTransformationDragon(id: Int): Resource<CharacterResponseById?> {
//        return try {
//            val result = httpClintTransformationDragon(id = id)
//            Resource.Success(data = result)
//        } catch (e: Exception) {
//            Resource.Error(e.message.toString())
//        }
//    }
//
//    suspend fun fetchFilterDragons(gender: String): Resource<DragonResponse?> {
//        return try {
//            val result = httpClintFilterDragon(gender = gender)
//            Resource.Success(data = result)
//        } catch (e: Exception) {
//            Resource.Error(e.message.toString())
//        }
//    }
//}