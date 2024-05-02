package com.example.kinokz.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.kinokz.adapter.MovieAdapter
import com.example.kinokz.databinding.MainpageBinding
import com.example.kinokz.model.Movie
import com.example.kinokz.model.MovieResponse
import com.example.kinokz.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieFragment : Fragment() {

    private var _binding: MainpageBinding? = null
    private val binding get() = _binding!!

    private var adapter: MovieAdapter? = null

    companion object {
        fun newInstance() = MovieFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainpageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = MovieAdapter { movie ->
            // Обработка клика на фильм
            Toast.makeText(context, movie.results.map { it.title }.toString(), Toast.LENGTH_SHORT).show()
        }

        binding.recyclerView.adapter = adapter

        // Вызываем метод загрузки фильмов при создании фрагмента
        fetchMovies()
    }

    private fun fetchMovies() {
        ApiClient.instance.fetchMovieList().enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful) {
                    val movieResponse = response.body()
                    movieResponse?.let {
                        Log.d("MovieFragment", "Movies successfully retrieved: ${it.results.size}")
                        adapter?.submitList(it.results.map{it})
                    }
                } else {
                    val errorMessage = "Error: ${response.code()} ${response.message()}"
                    Log.e("MovieFragment", errorMessage)
                    Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                val errorMessage = "Error: ${t.message}"
                Log.e("MovieFragment", errorMessage)
                Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
            }
        })


}

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


