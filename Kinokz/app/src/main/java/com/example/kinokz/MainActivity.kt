package com.example.kinokz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kinokz.adapter.MovieAdapter
import com.example.kinokz.databinding.ActivityMainBinding
import com.example.kinokz.model.ComingSoonSection
import com.example.kinokz.model.GeneralSection
import com.example.kinokz.model.HeaderSection
import com.example.kinokz.model.Movie
import com.example.kinokz.model.NowPlayingSection

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val images = listOf(R.drawable.along,R.drawable.avengars, R.drawable.boyfriend,
            R.drawable.lega, R.drawable.no_name, R.drawable.notice_red, R.drawable.queen
        )

        val comingSoonMovies = listOf(
            Movie("Avatar 2: The Way Of Water ", "Adventure, Sci-fi", "20.12.2022", R.drawable.avatar),
            Movie("Film 2", "Genre 2", "22.01.2023", R.drawable.quant),
            Movie("Batman v Superman:Dawn of Justice", "Action, Sci-fi", "25.03.2016", R.drawable.batman),
        )

        val mainRecyclerView: RecyclerView = findViewById(R.id.mainRecyclerView)
        mainRecyclerView.layoutManager = LinearLayoutManager(this)

        val sections = listOf(
            HeaderSection("Cinema"),
            NowPlayingSection("Now Playing", images),
            ComingSoonSection("Coming Soon", comingSoonMovies),
            GeneralSection("Promo & Discount"),

        )
        mainRecyclerView.adapter = MovieAdapter(sections)
    }
}