package com.cantrk.foodappcleanarchitecture

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.cantrk.foodappcleanarchitecture.databinding.ActivityMainBinding
import com.cantrk.foodappcleanarchitecture.ui.CategoryFragment
import com.cantrk.foodappcleanarchitecture.ui.FavoriteFragment
import com.cantrk.foodappcleanarchitecture.ui.HomeFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController

        navHostFragment?.findNavController()?.addOnDestinationChangedListener { _, destination, _ ->
            binding.bottomNavigation.visibility = when (destination.id) {
                R.id.categoryFragment, R.id.homeFragment, R.id.favoriteFragment -> View.VISIBLE
                else -> View.GONE
            }
        }
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.homeFragment -> navController.navigate(R.id.homeFragment)
                R.id.favoriteFragment -> navController.navigate(R.id.favoriteFragment)
                R.id.categoryFragment -> navController.navigate(R.id.categoryFragment)
            }
            true
        }
    }
}