package com.example.kinokz.diffUtil

import androidx.recyclerview.widget.DiffUtil
import com.example.kinokz.model.Section

class MovieDiffCallback : DiffUtil.ItemCallback<Section>() {
    override fun areItemsTheSame(oldItem: Section, newItem: Section): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: Section, newItem: Section): Boolean {
        return oldItem == newItem
    }
}

