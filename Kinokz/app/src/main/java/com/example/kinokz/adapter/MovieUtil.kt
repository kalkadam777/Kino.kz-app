package com.example.historicalfigures.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.kinokz.model.Movie
import com.example.kinokz.model.MovieResponse

class MovieUtil : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem:Movie, newItem: Movie): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }

}