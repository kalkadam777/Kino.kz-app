package com.example.kinokz.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kinokz.network.ApiClient
import kotlinx.coroutines.launch
import retrofit2.HttpException

class MovieSearchViewModel : ViewModel() {
    private val _movieSearchState = MutableLiveData<MovieSearchState>()
    val movieSearchState: LiveData<MovieSearchState> get() = _movieSearchState

    fun searchMovies(query: String) {
        _movieSearchState.value = MovieSearchState.Loading(true)

        viewModelScope.launch {
            try {
                val response = ApiClient.instance.searchMovies(query)
                val movies = response.results
                _movieSearchState.value = MovieSearchState.Success(movies)
            } catch (e: HttpException) {
                _movieSearchState.value = MovieSearchState.Error(e.message)
            } catch (e: Exception) {
                _movieSearchState.value = MovieSearchState.Error(e.message)
            } finally {
                _movieSearchState.value = MovieSearchState.Loading(false)
            }
        }
    }
}
