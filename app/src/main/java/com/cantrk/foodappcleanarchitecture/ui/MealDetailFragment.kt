package com.cantrk.foodappcleanarchitecture.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import com.cantrk.foodappcleanarchitecture.BaseFragment
import com.cantrk.foodappcleanarchitecture.databinding.FragmentMealDetailBinding
import com.cantrk.foodappcleanarchitecture.viewmodel.MealDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MealDetailFragment : BaseFragment<FragmentMealDetailBinding>(FragmentMealDetailBinding::inflate)  {

    private val viewModel : MealDetailViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getDataFromViewModel()
    }

    private fun getDataFromViewModel(){
        viewModel.viewModelScope.launch {
            viewModel.mealDetail.collectLatest {
                binding.apply {
                    if (it.isLoading){
                        mealDataConstraint.isVisible=false
                        progressBar.isVisible=true
                    }
                    if (it.error != ""){
                        mealDataConstraint.isVisible=false
                        progressBar.isVisible=false
                        errorText.isVisible=true
                        errorText.text=it.error
                    }
                    if (it.category?.isNotEmpty()==true){
                        mealDataConstraint.isVisible=true
                        progressBar.isVisible=false
                    }
                }
            }
        }
    }
}