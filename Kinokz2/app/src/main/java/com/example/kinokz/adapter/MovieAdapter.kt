package com.example.kinokz.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.historicalfigures.adapter.MovieUtil
import com.example.kinokz.databinding.MovieBinding
import com.example.kinokz.model.MovieResponse

class MovieAdapter(
    private val onMovieRemoved: (MovieResponse) -> Unit
) : ListAdapter <MovieResponse, MovieAdapter.ViewHolder>(MovieUtil()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            MovieBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(
        private val binding: MovieBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: MovieResponse) {
            with(binding) {
                movieName.text = movie.results.map{ it.title }.toString()
            }

        }
    }
}