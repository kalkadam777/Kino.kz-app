package com.example.kinokz.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.kinokz.R
import com.example.kinokz.model.Movie
import com.example.kinokz.model.Movie2

class ImageAdapter(private val images: List<Movie>) : RecyclerView.Adapter<ImageAdapter.ViewHolder>(){
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val imageView: ImageView = view.findViewById(R.id.image)
        private val titleTextView: TextView = view.findViewById(R.id.main_title)
        private val overviewTextView: TextView = view.findViewById(R.id.desc_text)
        private val ratingTextView: TextView = view.findViewById(R.id.rating)

        fun bind(movie: Movie) {
            titleTextView.text = movie.title
            overviewTextView.text = movie.releaseDate
            ratingTextView.text = movie.voteAverage

            val imageUrl = "https://image.tmdb.org/t/p/original${movie.posterPath}"
            Glide.with(imageView.context).
                load(imageUrl).
                into(imageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = images.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val imageUrl = "https://image.tmdb.org/t/p/original${images[position]}"
//        Glide.with(holder.imageView.context).load(imageUrl).into(holder.imageView)
        holder.bind(images[position])
    }
}