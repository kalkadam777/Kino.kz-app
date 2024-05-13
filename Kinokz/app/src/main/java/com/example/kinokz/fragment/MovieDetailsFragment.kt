package com.example.kinokz.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.kinokz.databinding.FragmentMovieDetailsBinding

class MovieDetailsFragment : Fragment() {

    private var _binding: FragmentMovieDetailsBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<MovieDetailsFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            val imageUrl = "https://image.tmdb.org/t/p/original${args.movieImage}"
            Glide.with(movieImage.context).
            load(imageUrl).
            into(movieImage)
            movieTitle.text = args.movieTitle
            movieRating.text = args.movieRating
            movieOverview.text = args.movieOverview
            movieReleaseDate.text = args.movieReleaseDate
            backButton.setOnClickListener {
                findNavController().navigateUp()
            }

        }
    }

}