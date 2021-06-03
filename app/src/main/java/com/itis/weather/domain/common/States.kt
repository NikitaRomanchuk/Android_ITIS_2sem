package com.itis.weather.domain.common

sealed class UiState {
    object Loading : UiState()
    object Success : UiState()
    class Error(val message: String) : UiState()
}

sealed class ResourceState<out T> {
    object Loading : ResourceState<Nothing>()
    data class Fetched<T>(val item: T) : ResourceState<T>()
    class Error(val message: String) : ResourceState<Nothing>()
}