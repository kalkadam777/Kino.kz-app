package com.example.kinokz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kinokz.adapter.ImageAdapter
import com.example.kinokz.adapter.MovieAdapter
import com.example.kinokz.databinding.ActivityMainBinding
import com.example.kinokz.model.GeneralSection
import com.example.kinokz.model.HeaderSection
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

        val mainRecyclerView: RecyclerView = findViewById(R.id.mainRecyclerView)
        mainRecyclerView.layoutManager = LinearLayoutManager(this)
        val sections = listOf(
            HeaderSection("Cinema"),
            NowPlayingSection("Now Playing", images),
            GeneralSection("Coming Soon"),
            GeneralSection("Promo & Discount"),
            GeneralSection("Service"),
            GeneralSection("Movie News")
        )
        mainRecyclerView.adapter = MovieAdapter(sections)
    }
}