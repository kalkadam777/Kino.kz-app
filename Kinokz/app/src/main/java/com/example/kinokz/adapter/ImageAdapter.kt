package com.example.kinokz.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.kinokz.R
import com.example.kinokz.databinding.ItemImageBinding
import com.example.kinokz.databinding.NowPlayingItemBinding
import com.example.kinokz.diffUtil.ImageDiffCallback
import com.example.kinokz.model.Movie
import com.example.kinokz.model.Movie2

class ImageAdapter(
    private val onMovieClick: (Movie) -> Unit
) : ListAdapter<Movie, ImageAdapter.ViewHolder>(ImageDiffCallback()){
    class ViewHolder(
        val binding: ItemImageBinding,
        val onMovieClick: (Movie) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            with(binding) {
                val imageUrl = "https://image.tmdb.org/t/p/original${movie.posterPath}"
                Glide.with(image.context).
                load(imageUrl).
                into(image)

                mainTitle.text = movie.title
                descText.text = movie.releaseDate
                rating.text = movie.voteAverage

                root.setOnClickListener(){
                    onMovieClick(movie)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, onMovieClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}