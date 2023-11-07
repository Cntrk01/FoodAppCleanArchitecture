package com.cantrk.foodappcleanarchitecture.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import com.cantrk.foodappcleanarchitecture.util.BaseFragment
import com.cantrk.foodappcleanarchitecture.databinding.FragmentNavFavoriteBinding
import com.cantrk.foodappcleanarchitecture.viewmodel.MealGetFromDatabaseViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class NavFavoriteFragment : BaseFragment<FragmentNavFavoriteBinding>(FragmentNavFavoriteBinding::inflate) {
    private val mealDatabaseViewModel : MealGetFromDatabaseViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getMealsDb()
    }
    private fun getMealsDb(){
        with(mealDatabaseViewModel){
            viewModelScope.launch {
                getAllMeal()
                getAllMeal.collectLatest {
                    if (it.getRoomList != null){
                        Log.e("data123",it.getRoomList.toString())
                    }
                }
            }
        }
    }
}