package com.example.kinokz.network

import retrofit2.Call
import com.example.kinokz.model.Movie
import com.example.kinokz.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {
    @GET("3/movie/popular")
    fun fetchMovieList(): Call<MovieResponse>
}
