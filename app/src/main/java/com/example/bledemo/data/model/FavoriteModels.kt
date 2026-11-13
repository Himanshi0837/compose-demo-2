package com.example.bledemo.data.model

import com.google.gson.annotations.SerializedName

data class FavoriteLinksRequest(
    @SerializedName("pageIndex")
    val pageIndex: Int = 1
)

data class FavoriteLinksResponseModel(
    @SerializedName("status")
    val status: String? = null,

    @SerializedName("msg")
    val msg: String? = null,

    @SerializedName("data")
    val data: List<FavoriteLinkDatum>? = null,

    @SerializedName("pagination")
    val pagination: PaginationModel? = null
)

data class FavoriteLinkDatum(
    @SerializedName("linkId")
    val linkId: Int? = null,

    @SerializedName("label")
    val label: String? = null,

    @SerializedName("description")
    val description: String? = null,

    @SerializedName("imageUrl")
    val imageUrl: String? = null,

    @SerializedName("url")
    val url: String? = null,

    @SerializedName("hashtags")
    val hashtags: List<String>? = null,

    @SerializedName("isFavorite")
    val isFavorite: Int? = 1,

    @SerializedName("isReminder")
    val isReminder: Int? = 0,

    @SerializedName("createdAt")
    val createdAt: String? = null
) {
    val displayId: Int
        get() = linkId ?: 0

    val displayTitle: String
        get() = label?.takeIf { it.isNotBlank() } ?: "Link #$displayId"

    val displayDescription: String
        get() = description?.takeIf { it.isNotBlank() } ?: (url ?: "")

    val isFav: Boolean
        get() = isFavorite == 1

    val formattedDate: String
        get() {
            val raw = createdAt ?: return ""
            return try {
                val inputFormat = java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss", java.util.Locale.ENGLISH)
                val date = inputFormat.parse(raw)
                if (date != null) {
                    val outputFormat = java.text.SimpleDateFormat("dd-MMM-yyyy", java.util.Locale.ENGLISH)
                    outputFormat.format(date)
                } else {
                    raw
                }
            } catch (_: Exception) {
                raw
            }
        }
}

data class PaginationModel(
    @SerializedName("currentPage")
    val currentPage: Int? = 1,

    @SerializedName("totalPages")
    val totalPages: Int? = 1,

    @SerializedName("perPage")
    val perPage: Int? = 10,

    @SerializedName("totalItems")
    val totalItems: Int? = 0
)
