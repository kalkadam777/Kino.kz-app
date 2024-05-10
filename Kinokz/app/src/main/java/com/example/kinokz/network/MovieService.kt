package com.example.kinokz.network

import retrofit2.Call
import com.example.kinokz.model.Movie
import com.example.kinokz.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {
    @GET("3/movie/now_playing")
    fun fetchMovieList(): Call<MovieResponse>

    @GET("3/movie/upcoming")
    fun fetchMovieList2(): Call<MovieResponse>
}
