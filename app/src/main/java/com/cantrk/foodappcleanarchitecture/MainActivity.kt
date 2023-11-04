package com.cantrk.foodappcleanarchitecture

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.cantrk.foodappcleanarchitecture.databinding.ActivityMainBinding
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
                R.id.navCategoryFragment, R.id.navHomeFragment, R.id.navFavoriteFragment -> View.VISIBLE
                else -> View.GONE
            }
        }
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navHomeFragment -> navController.navigate(R.id.navHomeFragment)
                R.id.navFavoriteFragment -> navController.navigate(R.id.navFavoriteFragment)
                R.id.navCategoryFragment -> navController.navigate(R.id.navCategoryFragment)
            }
            true
        }
    }
}