package com.example.kinokz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kinokz.adapter.MovieAdapter
import com.example.kinokz.databinding.ActivityMainBinding
//import com.example.kinokz.fragment.NowPlayingFragment
import com.example.kinokz.model.ComingSoonSection
import com.example.kinokz.model.HeaderSection
import com.example.kinokz.model.MovieResponse
import com.example.kinokz.model.NowPlayingSection
import com.example.kinokz.model.PromoSection
import com.example.kinokz.model.Promotion
import com.example.kinokz.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainRecyclerView = binding.mainRecyclerView
        mainRecyclerView.layoutManager = LinearLayoutManager(this)

        val client = ApiClient.instance

        // Выполняем первый запрос
        val responseNowPlaying = client.fetchMovieList()
        responseNowPlaying.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful) {
                    val nowPlayingMovies = response.body()?.results ?: emptyList()
                    // Выполняем второй запрос
                    val responseUpcoming = client.fetchMovieList2()
                    responseUpcoming.enqueue(object : Callback<MovieResponse> {
                        override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                            if (response.isSuccessful) {
                                val upcomingMovies = response.body()?.results ?: emptyList()
                                // Создаем список секций с результатами запросов
                                val sections = listOf(
                                    HeaderSection("Cinema"),
                                    NowPlayingSection("Now Playing", nowPlayingMovies),
                                    ComingSoonSection("Coming Soon", upcomingMovies),
                                    PromoSection("Promo & Discount", listOf(Promotion("Get 20% off on all tickets this Friday!", R.drawable.promo)))
                                )
                                mainRecyclerView.adapter = MovieAdapter(sections)
                            } else {
                                // Обработка ошибки API
                            }
                        }

                        override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                            println("HttpResponse $t")
                        }
                    })
                } else {
                    // Обработка ошибки API
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                println("HttpResponse $t")
            }
        })
    }
}




//        val promoList = listOf(
//            Promotion("Get 20% off on all tickets this Friday!", R.drawable.promo),
//        )
//
//        val mainRecyclerView: RecyclerView = findViewById(R.id.mainRecyclerView)
//        mainRecyclerView.layoutManager = LinearLayoutManager(this)
//
//        val sections = listOf(
//            HeaderSection("Cinema"),
////            NowPlayingSection("Now Playing", movieIds),
////            ComingSoonSection("Coming Soon", comingSoonMovies),
//            PromoSection("Promo & Discount", promoList)
//        )
//        mainRecyclerView.adapter = MovieAdapter(sections)


//        if (savedInstanceState == null) {
//            supportFragmentManager.beginTransaction()
//                .replace(R.id.fragment_container, NowPlayingFragment())
//                .commit()
//        }


