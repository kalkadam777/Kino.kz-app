package com.example.kinokz.model

import com.google.gson.annotations.SerializedName


data class Movie(
    val id:Int,
    val title:String,
    val overview:String,
    @SerializedName("vote_average") val voteAverage:String,
    @SerializedName("poster_path") val posterPath:String,
    @SerializedName("release_date") val releaseDate:String
)