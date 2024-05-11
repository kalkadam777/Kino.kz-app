package com.example.kinokz.diffUtil

import androidx.recyclerview.widget.DiffUtil
import com.example.kinokz.model.Movie

class ImageDiffCallback : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }
}

