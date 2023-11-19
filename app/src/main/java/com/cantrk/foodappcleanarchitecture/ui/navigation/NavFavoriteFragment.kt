package com.cantrk.foodappcleanarchitecture.ui.navigation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.cantrk.foodappcleanarchitecture.adapter.FavoriteListAdapter
import com.cantrk.foodappcleanarchitecture.util.BaseFragment
import com.cantrk.foodappcleanarchitecture.databinding.FragmentNavFavoriteBinding
import com.cantrk.foodappcleanarchitecture.viewmodel.MealGetFromDatabaseViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class NavFavoriteFragment : BaseFragment<FragmentNavFavoriteBinding>(FragmentNavFavoriteBinding::inflate) {
    private val mealDatabaseViewModel : MealGetFromDatabaseViewModel by viewModels()
    private lateinit var favoriteAdapter : FavoriteListAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        getMealsDb()
        clickItem()
    }
    private fun getMealsDb(){
        with(mealDatabaseViewModel){
            viewModelScope.launch {
                getAllMeal()
                getAllMeal.collectLatest {
                    if (it.getRoomList != null){
                        favoriteAdapter.setFavoriteList(it.getRoomList)
                    }
                }
            }
        }
    }
    private fun initAdapter(){
        favoriteAdapter= FavoriteListAdapter()
        binding.apply {
            recyclerView.adapter=favoriteAdapter
            recyclerView.layoutManager=LinearLayoutManager(requireContext())
        }
    }
    private fun clickItem(){
        favoriteAdapter.clickItem = {
            it.mealId?.let { it1 -> mealDatabaseViewModel.setDetailId(it1) }
            val action=NavFavoriteFragmentDirections.actionNavFavoriteFragmentToMealDetailFragment()
            findNavController().navigate(action)
        }
    }
}