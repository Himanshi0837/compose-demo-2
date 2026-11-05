package com.example.bledemo.data.model

import com.google.gson.annotations.SerializedName

/**
 * Response model for the Categories API endpoint.
 * Corresponds to CategoryListResponseModel in Flutter.
 */
data class CategoryListResponseModel(
    @SerializedName("status")
    val status: String? = null,

    @SerializedName("msg")
    val msg: String? = null,

    @SerializedName("data")
    val data: List<CategoryDatum>? = null
)

data class CategoryDatum(
    @SerializedName("categoryId")
    val categoryId: Int? = null,

    @SerializedName("id")
    val id: Int? = null,

    @SerializedName("categoryName")
    val categoryName: String? = null,

    @SerializedName("image")
    val image: String? = null,

    @SerializedName("imageUrl")
    val imageUrl: String? = null,

    @SerializedName("isDefault")
    val isDefault: Int? = null,

    @SerializedName("isEnabled")
    val isEnabled: Int? = null,

    @SerializedName("totalLinks")
    val totalLinks: Int? = null,

    @SerializedName("lastLinkAddedAt")
    val lastLinkAddedAt: String? = null,

    @SerializedName("en")
    val en: LanguageItem? = null,

    @SerializedName("hi")
    val hi: LanguageItem? = null,

    @SerializedName("es")
    val es: LanguageItem? = null,

    @SerializedName("fr")
    val fr: LanguageItem? = null,

    @SerializedName("isShared")
    val isShared: Boolean? = false
) {
    val resolvedId: Int
        get() = categoryId ?: id ?: 0

    val resolvedName: String
        get() = en?.name ?: categoryName ?: "Category #$resolvedId"

    val resolvedImageUrl: String?
        get() = imageUrl ?: image
}

data class LanguageItem(
    @SerializedName("name")
    val name: String? = null
)

