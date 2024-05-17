package com.example.kinokz.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kinokz.model.Movie
import com.example.kinokz.model.MovieResponse
import com.example.kinokz.network.ApiClient
import kotlinx.coroutines.launch
import retrofit2.HttpException

class MovieViewModel : ViewModel() {
    private val _nowPlayingMovies = MutableLiveData<List<Movie>>()
    val nowPlayingMovies: LiveData<List<Movie>> get() = _nowPlayingMovies

    private val _comingSoonMovies = MutableLiveData<List<Movie>>()
    val comingSoonMovies: LiveData<List<Movie>> get() = _comingSoonMovies

    private val client = ApiClient.instance

    init {
        fetchNowPlayingMovies()
        fetchComingSoonMovies()
    }

    private fun fetchNowPlayingMovies() {
        viewModelScope.launch {
            try {
                val response = client.fetchMovieList().results
                _nowPlayingMovies.postValue(response)
            } catch (e: HttpException) {
                // Handle error
            } catch (e: Exception) {
                // Handle error
            }
        }
    }

    private fun fetchComingSoonMovies() {
        viewModelScope.launch {
            try {
                val response = client.fetchMovieList2().results
                _comingSoonMovies.postValue(response)
            } catch (e: HttpException) {
                // Handle error
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
}