package com.cantrk.foodappcleanarchitecture

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.cantrk.foodappcleanarchitecture.databinding.ActivityMainBinding
import com.cantrk.foodappcleanarchitecture.ui.CategoryFragment
import com.cantrk.foodappcleanarchitecture.ui.FavoriteFragment
import com.cantrk.foodappcleanarchitecture.ui.HomeFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val fragment1 = HomeFragment()
    private val fragment2 = FavoriteFragment()
    private val fragment3 = CategoryFragment()
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController

        binding.apply {
            bottomNavigation.setOnItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.homeFragment -> navController.navigate(R.id.homeFragment)
                    R.id.favoriteFragment -> navController.navigate(R.id.favoriteFragment)
                    R.id.categoryFragment -> navController.navigate(R.id.categoryFragment)
                }
                true
            }
        }
    }
}