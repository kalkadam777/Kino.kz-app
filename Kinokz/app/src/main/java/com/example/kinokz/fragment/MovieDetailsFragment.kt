package com.example.kinokz.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.kinokz.databinding.FragmentMovieDetailsBinding
import com.example.kinokz.model.MovieDetails
import com.example.kinokz.viewmodel.MovieDetailsState
import com.example.kinokz.viewmodel.MovieDetailsViewModel


class MovieDetailsFragment : Fragment() {

    private var _binding: FragmentMovieDetailsBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<MovieDetailsFragmentArgs>()
    private lateinit var viewModel: MovieDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(MovieDetailsViewModel::class.java)
        viewModel.movieDetailsState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is MovieDetailsState.Loading -> {
                    binding.progressBar1.isVisible = state.isLoading
                    binding.contentLayout.isVisible = !state.isLoading
                }

                is MovieDetailsState.Success -> {
                    val movieDetails = state.movieDetails
                    updateUI(movieDetails)
                }

                is MovieDetailsState.Error -> {
                    binding.progressBar1.visibility = View.GONE
                }
            }
        }

        viewModel.fetchMovieDetails(args.movieId)
    }

    private fun formatRuntime(runtime: Int): String {
        val hours = runtime / 60
        val minutes = runtime % 60
        return "${hours}h${minutes}m"
    }

    private fun updateUI(movieDetails: MovieDetails) {
        with(binding) {
            val imageUrl = "https://image.tmdb.org/t/p/original${movieDetails.backdropPath}"
            Glide.with(movieImage.context)
                .load(imageUrl)
                .into(movieImage)
            movieTitle.text = movieDetails.title
            movieRating.text = movieDetails.voteAverage
            movieOverview.text = movieDetails.overview
            movieReleaseDate.text = "${formatRuntime(movieDetails.runtime)} • ${movieDetails.releaseDate}"
            movieRating.text = movieDetails.voteAverage
//            movieDuration.text = formatRuntime(movieDetails.runtime)
            movieGenre.text = movieDetails.genres.first().name
            backButton.setOnClickListener {
                requireActivity().onBackPressed() // Use requireActivity() instead of findNavController().navigateUp()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
