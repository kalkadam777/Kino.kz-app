package com.example.kinokz.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kinokz.databinding.ComingSoonMovieItemBinding
import com.example.kinokz.diffUtil.ComingSoonDiffCallback
import com.example.kinokz.model.Movie

class ComingSoonMoviesAdapter (
    private val onMovieClick: (Movie) -> Unit
): ListAdapter<Movie, ComingSoonMoviesAdapter.MovieViewHolder>(ComingSoonDiffCallback()) {


    class MovieViewHolder(private val binding: ComingSoonMovieItemBinding, val onMovieClick: (Movie) -> Unit) : RecyclerView.ViewHolder(binding.root) {


        fun bind(movie: Movie) {
            with(binding) {
                val imageUrl = "https://image.tmdb.org/t/p/original${movie.posterPath}"

                Glide.with(movieImage.context).
                load(imageUrl).
                into(movieImage)

                movieTitle.text = movie.title
                movieReleaseDate.text = movie.releaseDate

                root.setOnClickListener(){
                    onMovieClick(movie)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ComingSoonMovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding, onMovieClick)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}
