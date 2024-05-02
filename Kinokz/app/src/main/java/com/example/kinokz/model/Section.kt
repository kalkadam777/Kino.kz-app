package com.example.kinokz.model

sealed class Section(val title: String)

class HeaderSection(title: String) : Section(title)
class NowPlayingSection(title: String, val movies: List<Int>) : Section(title)

class ComingSoonSection(title: String, val movies: List<Movie>) : Section(title)
class GeneralSection(title: String) : Section(title)
