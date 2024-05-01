package com.example.historicalfigures.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.kinokz.model.Movie
import com.example.kinokz.model.MovieResponse

class MovieUtil : DiffUtil.ItemCallback<MovieResponse>() {
    override fun areItemsTheSame(oldItem:MovieResponse, newItem: MovieResponse): Boolean {
        val oldTitles = oldItem.results.map { it.title }
        val newTitles = newItem.results.map { it.title }
        return oldTitles == newTitles

    }

    override fun areContentsTheSame(oldItem: MovieResponse, newItem: MovieResponse): Boolean {
        return oldItem == newItem
    }

}