package com.example.kinokz.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.kinokz.R
import com.example.kinokz.databinding.GeneralItemBinding
import com.example.kinokz.databinding.HeaderItemBinding
import com.example.kinokz.databinding.NowPlayingItemBinding
import com.example.kinokz.databinding.PromoItemBinding
import com.example.kinokz.databinding.SectionComingSoonBinding
import com.example.kinokz.diffUtil.MovieDiffCallback
import com.example.kinokz.model.ComingSoonSection
import com.example.kinokz.model.GeneralSection
import com.example.kinokz.model.HeaderSection
import com.example.kinokz.model.Movie
import com.example.kinokz.model.NowPlayingSection
import com.example.kinokz.model.Section
//import com.example.kinokz.databinding.MovieBinding
import com.example.kinokz.model.PromoSection

class MovieAdapter(private val onMovieClick: (Movie) -> Unit): ListAdapter<Section, RecyclerView.ViewHolder>(MovieDiffCallback()){
    companion object {
        const val TYPE_NOW_PLAYING = 0
        const val TYPE_GENERAL = 1
        const val TYPE_HEADER = 2
        const val TYPE_COMING_SOON = 3
        const val TYPE_PROMO = 4
    }

    private var items: List<Any> = emptyList()
    private var originalList: List<Section> = emptyList()


    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is NowPlayingSection -> TYPE_NOW_PLAYING
            is HeaderSection -> TYPE_HEADER
            is ComingSoonSection -> TYPE_COMING_SOON
            is PromoSection -> TYPE_PROMO
            else -> TYPE_GENERAL
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_NOW_PLAYING -> {
                val binding = NowPlayingItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                NowPlayingViewHolder(binding, onMovieClick)
            }
            TYPE_HEADER -> {
                val binding = HeaderItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return HeaderViewHolder(binding)
            }
            TYPE_COMING_SOON -> {
                val binding = SectionComingSoonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return ComingSoonViewHolder(binding, onMovieClick)
            }
            TYPE_PROMO -> {
                val binding = PromoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return PromoAdapter.PromoViewHolder(binding)
            }
            else -> {
                val binding = GeneralItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return GeneralViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is NowPlayingViewHolder -> holder.bind(getItem(position) as NowPlayingSection)
            is GeneralViewHolder -> holder.bind(getItem(position) as GeneralSection)
            is ComingSoonViewHolder -> holder.bind(getItem(position) as ComingSoonSection)
            is PromoAdapter.PromoViewHolder -> holder.bind((getItem(position) as PromoSection).promotions[0])
        }
    }


    class HeaderViewHolder(private val binding: HeaderItemBinding) : RecyclerView.ViewHolder(binding.root) {
//        fun bind(section: HeaderSection) {
//            val title: TextView = itemView.findViewById(R.id.sectionTitle)
//            title.text = section.title
//        }
    }

    class NowPlayingViewHolder(private val binding: NowPlayingItemBinding, private val onMovieClick: (Movie) -> Unit) : RecyclerView.ViewHolder(binding.root) {
        fun bind(section: NowPlayingSection) {
            val adapter = ImageAdapter(onMovieClick)
            binding.recyclerViewHorizontal.layoutManager = LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL, false)
            binding.recyclerViewHorizontal.adapter = adapter
            adapter.submitList(section.movies)
        }
    }

    class GeneralViewHolder(private val binding: GeneralItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(section: GeneralSection) {
            with(binding) {
                sectionTitle.text = section.title
            }
        }
    }

    class ComingSoonViewHolder(private val binding: SectionComingSoonBinding, private val onMovieClick: (Movie) -> Unit) : RecyclerView.ViewHolder(binding.root) {
        fun bind(section: ComingSoonSection) {
            val adapter = ComingSoonMoviesAdapter(onMovieClick)
            binding.recyclerViewHorizontal.layoutManager = LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL, false)
            binding.recyclerViewHorizontal.adapter = adapter
            adapter.submitList(section.movies)
        }
    }
}