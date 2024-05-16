package com.example.kinokz.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kinokz.network.ApiClient
import kotlinx.coroutines.launch
import retrofit2.HttpException
import kotlinx.coroutines.async


class MovieDetailsViewModel : ViewModel() {
    private val _movieDetailsState = MutableLiveData<MovieDetailsState>()
    val movieDetailsState: LiveData<MovieDetailsState> get() = _movieDetailsState

    fun fetchMovieDetails(movieId: Int) {
        _movieDetailsState.value = MovieDetailsState.Loading(true)

        viewModelScope.launch {
            try {
                val movieDetailsDeferred = async { ApiClient.instance.fetchMovieDetails(movieId) }
                val movieDetails = movieDetailsDeferred.await()

                _movieDetailsState.value = MovieDetailsState.Success(movieDetails)
            } catch (e: HttpException) {
                _movieDetailsState.value = MovieDetailsState.Error(e.message)
            } catch (e: Exception) {
                _movieDetailsState.value = MovieDetailsState.Error(e.message)
            } finally {
                _movieDetailsState.value = MovieDetailsState.Loading(false)
            }
        }
    }
}


