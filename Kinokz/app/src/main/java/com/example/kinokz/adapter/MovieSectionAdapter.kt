package com.example.kinokz.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kinokz.databinding.MovieBinding
import com.example.kinokz.diffUtil.ImageDiffCallback
import com.example.kinokz.model.Movie

class MovieSectionAdapter(
    private val onMovieClick: (Movie) -> Unit
) : ListAdapter<Movie, MovieSectionAdapter.ViewHolder>(ImageDiffCallback()) {

    class ViewHolder(
        val binding: MovieBinding,
        val onMovieClick: (Movie) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        private fun formatNumber(input: String): String {
            val number = input.toDoubleOrNull()
            return if (number != null) {
                String.format("%.1f", number)
            } else {
                input
            }
        }
        fun bind(movie: Movie) {
            with(binding) {
                val imageUrl = "https://image.tmdb.org/t/p/original${movie.posterPath}"
                Glide.with(movieImage.context)
                    .load(imageUrl)
                    .into(movieImage)

                movieTitle.text = movie.title
                movieReleaseDate.text = movie.releaseDate
                rating.text = "${formatNumber(movie.voteAverage)} (${movie.voteCount})"

                root.setOnClickListener {
                    onMovieClick(movie)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = MovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, onMovieClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
