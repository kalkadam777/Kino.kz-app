package com.example.kinokz.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kinokz.databinding.ItemMovieBinding
import com.example.kinokz.diffUtil.ImageDiffCallback
import com.example.kinokz.model.Movie

class MovieListAdapter(
    private val onMovieClick: (Movie) -> Unit
) : ListAdapter<Movie, MovieListAdapter.MovieViewHolder>(ImageDiffCallback()) {

    class MovieViewHolder(private val binding: ItemMovieBinding, private val onMovieClick: (Movie) -> Unit) : RecyclerView.ViewHolder(binding.root) {


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

                Glide.with(imgMovie.context)
                    .load(imageUrl)
                    .into(imgMovie)

                movieName.text = movie.title
                movieTime.text = movie.releaseDate
                rating.text = "${formatNumber(movie.voteAverage)} (${movie.voteCount})"

                root.setOnClickListener {
                    onMovieClick(movie)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding, onMovieClick)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
