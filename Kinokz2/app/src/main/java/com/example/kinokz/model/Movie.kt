package com.example.kinokz.model

import com.google.gson.annotations.SerializedName

data class Movie(
    val title: String,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("release_date") val releaseDate: String
)