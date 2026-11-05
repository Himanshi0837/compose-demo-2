package com.example.bledemo.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bledemo.data.repository.CategoryRepository
import com.example.bledemo.data.repository.CategoryRepositoryImpl
import com.example.bledemo.ui.state.CategoryUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CategoryViewModel(
    private val repository: CategoryRepository = CategoryRepositoryImpl()
) : ViewModel() {

    private val _uiState = MutableStateFlow<CategoryUiState>(CategoryUiState.Loading)
    val uiState: StateFlow<CategoryUiState> = _uiState.asStateFlow()

    init {
        loadCategories()
    }

    fun loadCategories(token: String? = null) {
        viewModelScope.launch {
            _uiState.value = CategoryUiState.Loading
            val result = repository.fetchCategories(token)
            result.fold(
                onSuccess = { response ->
                    val list = response.data.orEmpty()
                    _uiState.value = CategoryUiState.Success(list)
                },
                onFailure = { error ->
                    _uiState.value = CategoryUiState.Error(
                        error.localizedMessage ?: "Failed to fetch categories"
                    )
                }
            )
        }
    }
}

