package com.example.kinokz.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kinokz.adapter.MovieAdapter
import com.example.kinokz.adapter.MovieListAdapter
import com.example.kinokz.databinding.FragmentMovieListBinding
import com.example.kinokz.model.Movie
import com.example.kinokz.viewmodel.MovieListState
import com.example.kinokz.viewmodel.MovieListViewModel
import com.example.kinokz.viewmodel.MovieSearchState
import com.example.kinokz.viewmodel.MovieSearchViewModel

class MovieListFragment : Fragment() {
    private var _binding: FragmentMovieListBinding? = null
    private val binding get() = _binding!!
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var searchAdapter: MovieListAdapter
    private val MovieListViewModel: MovieListViewModel by viewModels()
    private val movieSearchViewModel: MovieSearchViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieListBinding.inflate(inflater, container, false)
        Log.d("MovieListFragment", "onCreateView called")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("MovieListFragment", "onViewCreated called")

        movieAdapter = MovieAdapter(onMovieClick = { handleMovieClick(it) })
        searchAdapter = MovieListAdapter(onMovieClick = { handleMovieClick(it) })

        binding.mainRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.mainRecyclerView.adapter = movieAdapter

        observeNowPlayingViewModel()
        observeMovieSearchViewModel()

        MovieListViewModel.fetchMovieList()

        binding.searchView.setOnQueryTextListener(object : android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null && newText.isNotEmpty()) {
                    binding.mainRecyclerView.adapter = searchAdapter
                    movieSearchViewModel.searchMovies(newText)
                } else {
                    binding.mainRecyclerView.adapter = movieAdapter
                    MovieListViewModel.fetchMovieList()
                }
                return true
            }
        })

    }

    private fun observeNowPlayingViewModel() {
        MovieListViewModel.movieListState.observe(viewLifecycleOwner, Observer { state ->
            when (state) {
                is MovieListState.Success -> {
                    movieAdapter?.submitList(state.items)
                    binding.progressBar.isVisible = false
                    binding.mainRecyclerView.isVisible = true
                }
                is MovieListState.Loading -> {
                    binding.progressBar.isVisible = state.isLoading
                    binding.mainRecyclerView.isVisible = !state.isLoading
                }
                is MovieListState.Error -> {
                    binding.progressBar.isVisible = false
                    binding.mainRecyclerView.isVisible = true

                }
            }
        })
    }

    private fun observeMovieSearchViewModel() {
        movieSearchViewModel.movieSearchState.observe(viewLifecycleOwner, Observer { state ->
            when (state) {
                is MovieSearchState.Success -> {
                    searchAdapter?.submitList(state.items)
                    binding.progressBar.isVisible = false
                    binding.mainRecyclerView.isVisible = true
                }
                is MovieSearchState.Loading -> {
                    binding.progressBar.isVisible = state.isLoading
                    binding.mainRecyclerView.isVisible = !state.isLoading
                }
                is MovieSearchState.Error -> {
                    binding.progressBar.isVisible = false
                    binding.mainRecyclerView.isVisible = true
                }
            }
        })
    }

    private fun handleMovieClick(movie: Movie) {
        val direction = MovieListFragmentDirections.actionMovieListFragmentToMovieDetailsFragment(
            movie.id,
            movie.title,
            movie.posterPath,
            movie.voteAverage,
            movie.overview,
            movie.releaseDate
        )
        findNavController().navigate(direction)

    }
}






