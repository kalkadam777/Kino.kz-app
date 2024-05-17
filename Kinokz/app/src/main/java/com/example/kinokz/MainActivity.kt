package com.example.kinokz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
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
import com.example.kinokz.util.UserData
import com.google.android.material.bottomnavigation.BottomNavigationView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupNavigation()
    }

    private fun setupNavigation() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        val navGraph = navController.navInflater.inflate(R.navigation.nav_graph)
        when {
            UserData(this).isAuthorized() -> {
                navGraph.setStartDestination(R.id.movie_list_fragment)
            }
            !UserData(this).isAuthorized() -> {
                navGraph.setStartDestination(R.id.login_fragment)
            }
        }
        navController.graph = navGraph

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        NavigationUI.setupWithNavController(bottomNavigationView, navController)
    }
}



