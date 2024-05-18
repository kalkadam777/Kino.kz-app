package com.example.kinokz.viewmodel

import com.example.kinokz.model.Movie

sealed class MovieSearchState {
    data class Success(val items: List<Movie>) : MovieSearchState()
    data class Loading(val isLoading: Boolean) : MovieSearchState()
    data class Error(val message: String?) : MovieSearchState()
}