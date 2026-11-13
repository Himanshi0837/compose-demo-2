package com.example.bledemo.data.repository

import com.example.bledemo.data.api.ApiService
import com.example.bledemo.data.api.RetrofitClient
import com.example.bledemo.data.model.FavoriteLinksRequest
import com.example.bledemo.data.model.FavoriteLinksResponseModel
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface FavoriteRepository {
    suspend fun fetchFavoriteLinks(pageIndex: Int = 1, token: String? = null): Result<FavoriteLinksResponseModel>
}

class FavoriteRepositoryImpl(
    private val apiService: ApiService = RetrofitClient.apiService
) : FavoriteRepository {

    private val gson = Gson()

    override suspend fun fetchFavoriteLinks(pageIndex: Int, token: String?): Result<FavoriteLinksResponseModel> =
        withContext(Dispatchers.IO) {
            try {
                val authHeader = token?.takeIf { it.isNotBlank() }?.let {
                    if (it.startsWith("Bearer ")) it else "Bearer $it"
                }
                val response = apiService.getFavoriteLinks(
                    token = authHeader,
                    request = FavoriteLinksRequest(pageIndex = pageIndex)
                )
                if (response.isSuccessful && response.body() != null) {
                    Result.success(response.body()!!)
                } else {
                    val errorBody = response.errorBody()?.string()
                    val errorMessage = try {
                        val parsed = gson.fromJson(errorBody, FavoriteLinksResponseModel::class.java)
                        parsed?.msg ?: "Request failed with code ${response.code()}"
                    } catch (e: Exception) {
                        "Request failed with code ${response.code()}"
                    }
                    Result.failure(Exception(errorMessage))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
}

