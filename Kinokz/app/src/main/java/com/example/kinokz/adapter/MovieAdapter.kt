package com.example.kinokz.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.historicalfigures.adapter.MovieUtil
import com.example.kinokz.R
import com.example.kinokz.databinding.MovieBinding
import com.example.kinokz.model.Movie
import com.example.kinokz.model.MovieResponse

class MovieAdapter(
    private val onMovieRemoved: (MovieResponse) -> Unit
) : ListAdapter <Movie, MovieAdapter.ViewHolder>(MovieUtil()) {


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

        fun bind(movie: Movie) {
            with(binding) {
                Glide
                    .with(root.context)
                    .load("https://image.tmdb.org/t/p/original" + movie.posterPath)
                    .placeholder(R.drawable.avengars)
                    .into(movieImage)

                movieName.text = movie.title
                movieRelease.text = movie.releaseDate

            }

        }
    }
}