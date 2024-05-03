package com.example.kinokz.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kinokz.adapter.ImageAdapter
import com.example.kinokz.adapter.MovieAdapter
import com.example.kinokz.databinding.NowPlayingItemBinding
import com.example.kinokz.model.Movie
import com.example.kinokz.model.MovieResponse
import com.example.kinokz.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NowPlayingFragment : Fragment() {

    private var binding: NowPlayingItemBinding? = null

    private var adapter: MovieAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = NowPlayingItemBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val client = ApiClient.instance
        val response = client.fetchMovieList()

        response.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(
                call: Call<MovieResponse>,
                response: Response<MovieResponse>
            ) {
                println("HttpResponse ${response.body()}")

                if (response.isSuccessful) {
                    val movies = response.body()?.results ?: emptyList()
                    setupRecyclerView(movies)
                } else {
                    // Handle API error
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                println("HttpResponse $t")
            }


        })
    }

    private fun setupRecyclerView(movies: List<Movie>) {
        val movieIds = movies.map { it.posterPath }
        binding?.recyclerViewHorizontal?.adapter = ImageAdapter(movieIds)
        binding?.recyclerViewHorizontal?.layoutManager = LinearLayoutManager(context)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
