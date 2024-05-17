package com.example.kinokz.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kinokz.R
import com.example.kinokz.adapter.ComingSoonMoviesAdapter
import com.example.kinokz.adapter.ImageAdapter
import com.example.kinokz.adapter.MovieAdapter
import com.example.kinokz.databinding.FragmentMovieBinding
import com.example.kinokz.model.MovieResponse
import com.example.kinokz.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieFragment : Fragment() {

    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding!!


    private lateinit var movieAdapter: MovieAdapter
    private lateinit var imageAdapter: ImageAdapter
    private lateinit var comingSoonMoviesAdapter: ComingSoonMoviesAdapter
    private val client = ApiClient.instance

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        binding.recyclerViewMovies.adapter = movieAdapter
//        binding.recyclerViewMovies.layoutManager = LinearLayoutManager(context)

//        binding.radioGroup.setOnCheckedChangeListener { _, checkedId ->
//            when (checkedId) {
//                R.id.radioNowPlaying -> loadNowPlayingMovies()
//                R.id.radioComingSoon -> loadComingSoonMovies()
//            }
//        }
//        loadNowPlayingMovies()

    }

//    private fun loadNowPlayingMovies() {
//        client.fetchMovieList().enqueue(object : Callback<MovieResponse> {
//            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
//                if (response.isSuccessful) {
//                    val nowPlayingMovies = response.body()?.results ?: emptyList()
//                    movieAdapter.updateMovies(nowPlayingMovies)
//                } else {
//                    // Handle API error
//                }
//            }
//
//            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
//                // Handle failure
//            }
//        })
//    }
//
//    private fun loadComingSoonMovies() {
//        client.fetchMovieList2().enqueue(object : Callback<MovieResponse> {
//            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
//                if (response.isSuccessful) {
//                    val comingSoonMovies = response.body()?.results ?: emptyList()
//                    movieAdapter.updateMovies(comingSoonMovies)
//                } else {
//                    // Handle API error
//                }
//            }
//
//            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
//                // Handle failure
//            }
//        })
//    }
}