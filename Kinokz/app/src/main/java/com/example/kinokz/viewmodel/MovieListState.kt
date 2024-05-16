package com.example.kinokz.viewmodel

import com.example.kinokz.model.Section

sealed class MovieListState {
    data class Success(val items: List<Section>) : MovieListState()
    data class Loading(val isLoading: Boolean) : MovieListState()
    data class Error(val message: String?) : MovieListState()
}