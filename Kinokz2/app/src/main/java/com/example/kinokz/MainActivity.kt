package com.example.kinokz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kinokz.databinding.ActivityMainBinding
import com.example.kinokz.fragment.MovieFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val figureFragment = MovieFragment.newInstance()

        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragmentcont, figureFragment)
            .commit()
    }
}