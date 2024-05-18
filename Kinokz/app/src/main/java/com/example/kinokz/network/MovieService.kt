package com.example.kinokz.network

import com.example.kinokz.model.MovieDetails
import com.example.kinokz.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {
    @GET("3/movie/now_playing")
    suspend fun fetchMovieList(): MovieResponse

    @GET("3/movie/upcoming")
    suspend fun fetchMovieList2(): MovieResponse

    @GET("3/movie/{movie_id}")
    suspend fun fetchMovieDetails(@Path("movie_id") movieId: Int): MovieDetails

    @GET("3/search/movie")
    suspend fun searchMovies(@Query("query") query: String): MovieResponse
}
