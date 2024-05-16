package com.example.kinokz

//import com.example.kinokz.fragment.NowPlayingFragment
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.RecyclerView
import com.example.kinokz.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var mainRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupNavigation()

//        mainRecyclerView = binding.mainRecyclerView
//        mainRecyclerView.layoutManager = LinearLayoutManager(this)

//        val client = ApiClient.instance
////
//////         Выполняем первый запрос
////        val responseNowPlaying = client.fetchMovieList()
////        responseNowPlaying.enqueue(object : Callback<MovieResponse> {
////            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
////                if (response.isSuccessful) {
////                    val nowPlayingMovies = response.body()?.results ?: emptyList()
////                    // Выполняем второй запрос
////                    val responseUpcoming = client.fetchMovieList2()
////                    responseUpcoming.enqueue(object : Callback<MovieResponse> {
////                        override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
////                            if (response.isSuccessful) {
////                                val upcomingMovies = response.body()?.results ?: emptyList()
////                                // Создаем список секций с результатами запросов
////                                val sections = listOf(
////                                    HeaderSection("Cinema"),
////                                    NowPlayingSection("Now Playing", nowPlayingMovies),
////                                    ComingSoonSection("Coming Soon", upcomingMovies),
////                                    PromoSection("Promo & Discount", listOf(Promotion("Get 20% off on all tickets this Friday!", R.drawable.promo)))
////                                )
////                                val adapter = MovieAdapter(onMovieClick = {
////                                    // Обработка нажатия на элемент списка
////                                })
////                                adapter.submitList(sections)
////                                binding.mainRecyclerView.adapter = adapter
////                            } else {
////                                // Обработка ошибки API
////                            }
////                        }
////
////                        override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
////                            println("HttpResponse $t")
////                        }
////                    })
////                } else {
////                    // Обработка ошибки API
////                }
////            }
////
////            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
////                println("HttpResponse $t")
////            }
////        })
    }

    private fun setupNavigation() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        val navGraph = navController.navInflater.inflate(R.navigation.nav_graph)
        navGraph.setStartDestination(R.id.movie_list_fragment)

        navController.graph = navGraph
    }
}



