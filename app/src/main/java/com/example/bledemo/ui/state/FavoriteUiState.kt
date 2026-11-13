package com.example.bledemo.ui.state

import com.example.bledemo.data.model.FavoriteLinkDatum

sealed interface FavoriteUiState {
    data object Loading : FavoriteUiState
    data class Success(val items: List<FavoriteLinkDatum>) : FavoriteUiState
    data class Error(val message: String) : FavoriteUiState
}

