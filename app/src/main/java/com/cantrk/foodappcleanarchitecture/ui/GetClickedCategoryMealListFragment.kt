package com.cantrk.foodappcleanarchitecture.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.GridLayoutManager
import com.cantrk.foodappcleanarchitecture.BaseFragment
import com.cantrk.foodappcleanarchitecture.R
import com.cantrk.foodappcleanarchitecture.adapter.CategoryMealListAdapter
import com.cantrk.foodappcleanarchitecture.databinding.FragmentCategoryListBinding
import com.cantrk.foodappcleanarchitecture.states.MealState
import com.cantrk.foodappcleanarchitecture.viewmodel.CategoryListItemViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class GetClickedCategoryMealListFragment : BaseFragment<FragmentCategoryListBinding>(FragmentCategoryListBinding::inflate) {

    private val viewModel : CategoryListItemViewModel by viewModels()
    private lateinit var navCategoryAdapter: CategoryMealListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getMealCategoryItem()
    }

    private fun getMealCategoryItem(){
        navCategoryAdapter= CategoryMealListAdapter()

        binding.apply {
            recyclerView.adapter=navCategoryAdapter
            recyclerView.layoutManager=GridLayoutManager(requireContext(),2)
        }

        with(viewModel){
            viewModelScope.launch {
                listMealData.collectLatest {
                    fetchDataForUi(it)
                }
            }
        }
    }

    @SuppressLint("ResourceAsColor")
    private fun fetchDataForUi(it:MealState){
        with(binding) {
               progressBar.isVisible = it.isLoading
               recyclerView.isVisible = it.category?.isNotEmpty() == true
               errorMessage.isVisible = it.error.isNotEmpty()

            if (it.category?.isNotEmpty() == true) {
               navCategoryAdapter.setMealList(it.category)
            }
            toolbarText.text= CategoryListItemViewModel.categoryName
//örnek olarak kalsın diye

//           if (it.isLoading){
//                progressBar.visibility=View.VISIBLE
//                recyclerView.visibility=View.GONE
//                errorMessage.visibility=View.GONE
//           }
//           if (it.category?.isNotEmpty() == true){
//               progressBar.visibility=View.GONE
//               recyclerView.visibility=View.VISIBLE
//               errorMessage.visibility=View.GONE
//               it.category.let { it1 -> navCategoryAdapter.setMealList(it1) }
//           }
//           if (it.error.isNotEmpty()){
//               progressBar.visibility=View.GONE
//               recyclerView.visibility=View.GONE
//               errorMessage.visibility=View.VISIBLE
//           }
       }
    }
}