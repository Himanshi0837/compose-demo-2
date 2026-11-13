package com.example.bledemo.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bledemo.data.repository.FavoriteRepository
import com.example.bledemo.data.repository.FavoriteRepositoryImpl
import com.example.bledemo.ui.state.FavoriteUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FavoriteViewModel(
    private val repository: FavoriteRepository = FavoriteRepositoryImpl()
) : ViewModel() {

    private val _uiState = MutableStateFlow<FavoriteUiState>(FavoriteUiState.Loading)
    val uiState: StateFlow<FavoriteUiState> = _uiState.asStateFlow()

    init {
        loadFavorites()
    }

    fun loadFavorites(token: String? = null) {
        viewModelScope.launch {
            _uiState.value = FavoriteUiState.Loading
            val result = repository.fetchFavoriteLinks(pageIndex = 1, token = token)
            result.fold(
                onSuccess = { response ->
                    _uiState.value = FavoriteUiState.Success(response.data.orEmpty())
                },
                onFailure = { error ->
                    _uiState.value = FavoriteUiState.Error(
                        error.localizedMessage ?: "Failed to fetch favorite links"
                    )
                }
            )
        }
    }
}

