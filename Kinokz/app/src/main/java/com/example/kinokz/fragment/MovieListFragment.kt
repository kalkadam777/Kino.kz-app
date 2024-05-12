package com.example.kinokz.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kinokz.R
import com.example.kinokz.adapter.ImageAdapter
import com.example.kinokz.adapter.MovieAdapter
import com.example.kinokz.databinding.FragmentMovieListBinding
import com.example.kinokz.model.ComingSoonSection
import com.example.kinokz.model.HeaderSection
import com.example.kinokz.model.Movie
import com.example.kinokz.model.MovieResponse
import com.example.kinokz.model.NowPlayingSection
import com.example.kinokz.model.PromoSection
import com.example.kinokz.model.Promotion
import com.example.kinokz.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieListFragment : Fragment() {
    private var _binding: FragmentMovieListBinding? = null
    private val binding get() = _binding!!
//    private var adapter: ImageAdapter? = null
    private var adapter: MovieAdapter? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =  FragmentMovieListBinding.inflate(inflater, container, false)
        Log.d("MovieListFragment", "onCreateView called")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("MovieListFragment", "onViewCreated called")
        adapter = MovieAdapter (
            onMovieClick = {
                handleMovieClick(it)
            },
        )
        binding.mainRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.mainRecyclerView.adapter = adapter

        fetchMovieList(adapter!!)

//        binding.recyclerView.adapter = adapter
//
//        viewModel.fetchOfferList()
//
//        viewModel.movieListState.observe(viewLifecycleOwner){ state ->
//            when(state) {
//                is MovieListState.Success -> adapter?.submitList(state.items)
//                is MovieListState.Loading -> {
//                    with(binding){
//                        progressBar.isVisible = state.isLoading
//                        recyclerView.isVisible = !state.isLoading
//                    }
//                }
//                is MovieListState.Error -> {
//                    AlertDialog.Builder(requireContext())
//                        .setTitle(R.string.error_title)
//                        .setMessage(state.message ?: getString(R.string.error_message))
//                        .show()
//                }
//            }
//        }

    }

    private fun fetchMovieList(adapter: MovieAdapter) {
        ApiClient.instance.fetchMovieList().enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                Log.d("API", "onResponse called, success: ${response.isSuccessful}")
                if (response.isSuccessful) {
                    val movies = response.body()?.results ?: emptyList()
                    val sections = listOf(
                        HeaderSection("Cinema"),
                        NowPlayingSection("Now Playing", movies),
                        ComingSoonSection("Coming Soon", emptyList()),  // Update as needed
                        PromoSection("Promo & Discount", listOf(Promotion("Get 20% off on all tickets this Friday!", R.drawable.promo)))
                    )
                    adapter.submitList(sections)
                } else {
                    Log.e("API", "Error: ${response.errorBody()?.string()}")
                    // Handle error
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.e("API", "onFailure called: ${t.message}")
                println("HttpResponse $t")
            }
        })
    }

    private fun handleMovieClick(movie: Movie){
        /**
         * transition to movie details using Jetpack Navigation
         */

        val direction = MovieListFragmentDirections.actionMovieListFragmentToMovieDetailsFragment(movie.title)
        findNavController().navigate(direction)

    }

}