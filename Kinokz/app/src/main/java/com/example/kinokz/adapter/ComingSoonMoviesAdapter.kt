package com.example.kinokz.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kinokz.R
import com.example.kinokz.databinding.ComingSoonMovieItemBinding
import com.example.kinokz.diffUtil.ComingSoonDiffCallback
import com.example.kinokz.model.Movie
import com.example.kinokz.model.Movie2

class ComingSoonMoviesAdapter : ListAdapter<Movie, ComingSoonMoviesAdapter.MovieViewHolder>(ComingSoonDiffCallback()) {

    class MovieViewHolder(private val binding: ComingSoonMovieItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) {
            with(binding) {
                val imageUrl = "https://image.tmdb.org/t/p/original${movie.posterPath}"

                Glide.with(movieImage.context).
                load(imageUrl).
                into(movieImage)

                movieTitle.text = movie.title
                movieReleaseDate.text = movie.releaseDate
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ComingSoonMovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}
