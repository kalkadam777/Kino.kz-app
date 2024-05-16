package com.example.kinokz.viewmodel

import com.example.kinokz.model.MovieDetails

sealed class MovieDetailsState {
    data class Loading(val isLoading: Boolean) : MovieDetailsState()
    data class Success(val movieDetails: MovieDetails) : MovieDetailsState()
    data class Error(val errorMessage: String?) : MovieDetailsState()
}