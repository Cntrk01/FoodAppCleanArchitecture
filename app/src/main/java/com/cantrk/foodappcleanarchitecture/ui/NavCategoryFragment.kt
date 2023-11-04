package com.cantrk.foodappcleanarchitecture.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.cantrk.foodappcleanarchitecture.BaseFragment
import com.cantrk.foodappcleanarchitecture.adapter.NavCategoryAdapter
import com.cantrk.foodappcleanarchitecture.databinding.FragmentCategoryBinding
import com.cantrk.foodappcleanarchitecture.dataclass.Category
import com.cantrk.foodappcleanarchitecture.viewmodel.CategoryListItemViewModel
import com.cantrk.foodappcleanarchitecture.viewmodel.GetCategoriesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class NavCategoryFragment : BaseFragment<FragmentCategoryBinding>(FragmentCategoryBinding::inflate){

    private val viewModel : GetCategoriesViewModel by viewModels()
    private val categoryItemMealViewModel : CategoryListItemViewModel by viewModels()
    private lateinit var mealAdapter: NavCategoryAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeCategory()
    }

    private fun observeCategory(){
        viewModel.viewModelScope.launch {
            viewModel.categoryResponse.collectLatest {
                it.category?.let { it1 -> setCategoryAdapter(it1) }
            }
        }
    }

    private fun setCategoryAdapter(categoriesState: List<Category>){
        mealAdapter= NavCategoryAdapter()
        binding.apply {
            recyclerView.adapter=mealAdapter
            recyclerView.layoutManager=GridLayoutManager(requireContext(),2)
        }
        mealAdapter.setMealList(categoriesState)

        mealAdapter.setClickItemListener = {
            viewModel.setCategoryListItem(it.strCategory)
            val action=NavCategoryFragmentDirections.actionCategoryFragmentToCategoryListItemFragment()
            findNavController().navigate(action)
        }
    }
}