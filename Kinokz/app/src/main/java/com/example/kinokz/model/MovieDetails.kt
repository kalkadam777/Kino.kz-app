package com.example.kinokz.model

import com.google.gson.annotations.SerializedName

data class MovieDetails (
    val id: Int,
    val title: String,
    val overview: String,
    @SerializedName("vote_average") val voteAverage: String,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("backdrop_path") val backdropPath: String,
    @SerializedName("runtime") val runtime: Int,
    val genres: List<Genre>
)