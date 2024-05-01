package com.example.historicalfigures.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.kinokz.adapter.MovieAdapter
import com.example.kinokz.databinding.MainpageBinding
import com.example.kinokz.model.MovieResponse
import com.example.kinokz.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieFragment : Fragment() {

    private var _binding: MainpageBinding? = null

    private val binding get() = _binding!!

    companion object {
        fun newInstance() = MovieFragment()
    }

    private var adapter: MovieAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainpageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = MovieAdapter { MovieResponse ->
            Toast.makeText(context, MovieResponse.results.map{it.title}.toString(), Toast.LENGTH_SHORT).show()
        }

        binding.recyclerView.adapter = adapter

        fun fetchMovies() {
            ApiClient.instance.fetchMovieList().enqueue(object : Callback<List<MovieResponse>> {
                override fun onResponse(
                    call: Call<List<MovieResponse>>,
                    response: Response<List<MovieResponse>>
                ) {
                    if (response.isSuccessful) {
                        Log.d("PersonListFragment", "Success: ${response.body()}")
                        adapter?.submitList(response.body())
                    } else {
                        Log.e("PersonListFragment", "Error: ${response.errorBody()?.string()}")
                        Toast.makeText(context, "Error loading data", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<List<MovieResponse>>, t: Throwable) {
                    Toast.makeText(context, "No connection: ${t.message}", Toast.LENGTH_SHORT)
                        .show()
                }
            })
        }


    }
}



