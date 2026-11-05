package com.example.bledemo.data.repository

import com.example.bledemo.data.api.ApiService
import com.example.bledemo.data.api.RetrofitClient
import com.example.bledemo.data.model.CategoryListResponseModel
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface CategoryRepository {
    suspend fun fetchCategories(token: String? = null): Result<CategoryListResponseModel>
}

class CategoryRepositoryImpl(
    private val apiService: ApiService = RetrofitClient.apiService
) : CategoryRepository {

    private val gson = Gson()

    override suspend fun fetchCategories(token: String?): Result<CategoryListResponseModel> =
        withContext(Dispatchers.IO) {
            try {
                val response = apiService.getCategories(token = token)
                if (response.isSuccessful && response.body() != null) {
                    Result.success(response.body()!!)
                } else {
                    val errorBody = response.errorBody()?.string()
                    val errorMessage = try {
                        val parsed = gson.fromJson(errorBody, CategoryListResponseModel::class.java)
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

