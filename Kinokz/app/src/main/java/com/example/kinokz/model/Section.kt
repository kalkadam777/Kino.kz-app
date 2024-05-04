package com.example.kinokz.model

sealed class Section(val title: String)

class HeaderSection(title: String) : Section(title)
class NowPlayingSection(title: String, val movies: List<Movie>) : Section(title)

class ComingSoonSection(title: String, val movies: List<Movie2>) : Section(title)
class GeneralSection(title: String) : Section(title)

class PromoSection(title: String, val promotions: List<Promotion>) : Section(title)

data class Promotion(
    val description: String,
    val imageUrl: Int
)