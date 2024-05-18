package com.example.kinokz.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kinokz.R
import com.example.kinokz.model.*
import com.example.kinokz.network.ApiClient
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.HttpException


class MovieListViewModel : ViewModel() {
    private val _movieListState = MutableLiveData<MovieListState>()
    val movieListState: LiveData<MovieListState> get() = _movieListState

    init {
        fetchMovieList()
    }

    fun fetchMovieList() {
        _movieListState.value = MovieListState.Loading(true)

        viewModelScope.launch {
            try {
                // Concurrently fetch Now Playing and Coming Soon movies
                val nowPlayingDeferred = async { ApiClient.instance.fetchMovieList() }
                val comingSoonDeferred = async { ApiClient.instance.fetchMovieList2() }

                val nowPlayingMovies = nowPlayingDeferred.await().results
                val comingSoonMovies = comingSoonDeferred.await().results

                // Create sections with results from both API calls
                val sections = listOf(
                    HeaderSection("Cinema"),
                    NowPlayingSection("Now Playing", nowPlayingMovies),
                    ComingSoonSection("Coming Soon", comingSoonMovies),
                    PromoSection("Promo & Discount", listOf(Promotion("Get 20% off on all tickets this Friday!", R.drawable.promo)))
                )

                _movieListState.value = MovieListState.Success(sections)
            } catch (e: HttpException) {
                _movieListState.value = MovieListState.Error(e.message)
            } catch (e: Exception) {
                _movieListState.value = MovieListState.Error(e.message)
            } finally {
                _movieListState.value = MovieListState.Loading(false)
            }
        }
    }

}
