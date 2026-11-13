package com.example.bledemo.data.api

import com.example.bledemo.data.model.CategoryListResponseModel
import com.example.bledemo.data.model.FavoriteLinksRequest
import com.example.bledemo.data.model.FavoriteLinksResponseModel
import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {

    @POST("categories")
    suspend fun getCategories(
        @Header("Authorization") token: String? = null,
        @Body body: JsonObject = JsonObject()
    ): Response<CategoryListResponseModel>

    @POST("get-favorite-links")
    suspend fun getFavoriteLinks(
        @Header("Authorization") token: String? = null,
        @Body request: FavoriteLinksRequest = FavoriteLinksRequest()
    ): Response<FavoriteLinksResponseModel>
}
