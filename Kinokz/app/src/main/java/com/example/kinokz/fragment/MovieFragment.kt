package com.example.kinokz.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kinokz.R
import com.example.kinokz.adapter.ComingSoonMoviesAdapter
import com.example.kinokz.adapter.MovieAdapter
import com.example.kinokz.adapter.MovieSectionAdapter
import com.example.kinokz.databinding.FragmentMovieBinding
import com.example.kinokz.viewmodel.MovieViewModel

class MovieFragment : Fragment() {

    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding!!

    private lateinit var movieAdapter: MovieSectionAdapter
    private lateinit var comingSoonMoviesAdapter: ComingSoonMoviesAdapter
    private lateinit var viewModel: MovieViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MovieViewModel::class.java)

        movieAdapter = MovieSectionAdapter { movie -> /* Handle movie click */ }
        comingSoonMoviesAdapter = ComingSoonMoviesAdapter()

        binding.recyclerViewMovies.layoutManager = GridLayoutManager(context, 2)
        // Устанавливаем начальное состояние
        binding.radioNowPlaying.isChecked = true
        binding.recyclerViewMovies.adapter = movieAdapter

        viewModel.nowPlayingMovies.observe(viewLifecycleOwner) { movies ->
            movieAdapter.submitList(movies)
        }

        binding.radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.radioNowPlaying -> {
                    binding.recyclerViewMovies.adapter = movieAdapter
                    viewModel.nowPlayingMovies.observe(viewLifecycleOwner) { movies ->
                        movieAdapter.submitList(movies)
                    }
                }
                R.id.radioComingSoon -> {
                    binding.recyclerViewMovies.adapter = comingSoonMoviesAdapter
                    viewModel.comingSoonMovies.observe(viewLifecycleOwner) { movies ->
                        comingSoonMoviesAdapter.submitList(movies)
                    }
                }
            }
        }
    }

}
