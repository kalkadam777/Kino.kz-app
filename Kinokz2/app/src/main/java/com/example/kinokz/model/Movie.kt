package com.example.kinokz.model

data class Movie(
    val id: Int,
    val name: String,
    val name_rus: String,
    val name_origin: String,
    val rating: Double,
    val age_restriction: Int,
    val genres: List)