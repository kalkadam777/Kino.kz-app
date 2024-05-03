package com.example.kinokz.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kinokz.R
import com.example.kinokz.model.Movie
import com.example.kinokz.model.Movie2

class ComingSoonMoviesAdapter(private val movies: List<Movie2>) : RecyclerView.Adapter<ComingSoonMoviesAdapter.MovieViewHolder>() {

    class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val movieImage: ImageView = view.findViewById(R.id.movieImage)
        private val movieTitle: TextView = view.findViewById(R.id.movieTitle)
        private val movieGenre: TextView = view.findViewById(R.id.movieGenre)
        private val movieReleaseDate: TextView = view.findViewById(R.id.movieReleaseDate)

        fun bind(movie: Movie2) {
            // Здесь можно использовать библиотеку для загрузки изображений, например, Glide или Picasso
            Glide.with(itemView)
                .load(movie.imageUrl) // Или используйте movie.imageResId для локальных ресурсов
                .placeholder(R.drawable.quant) // Заглушка, если изображение еще не загружено
                .into(movieImage)

            movieTitle.text = movie.title
            movieGenre.text = movie.genre
            movieReleaseDate.text = movie.releaseDate
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.coming_soon_movie_item, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount(): Int = movies.size
}
