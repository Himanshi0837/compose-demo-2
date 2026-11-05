package com.example.bledemo.ui.state

import com.example.bledemo.data.model.CategoryDatum

sealed interface CategoryUiState {
    data object Loading : CategoryUiState
    data class Success(val categories: List<CategoryDatum>) : CategoryUiState
    data class Error(val message: String) : CategoryUiState
}

