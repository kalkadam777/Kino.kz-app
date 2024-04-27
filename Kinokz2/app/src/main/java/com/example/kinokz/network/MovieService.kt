package com.example.kinokz.network

import retrofit2.Call
import com.example.kinokz.model.Movie
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {
    @GET("historicalfigures")
    fun fetchPersonList(@Query("name") name: String):Call<List<Movie>>
}
