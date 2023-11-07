package com.cantrk.foodappcleanarchitecture.ui.navigation

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.cantrk.foodappcleanarchitecture.util.BaseFragment
import com.cantrk.foodappcleanarchitecture.adapter.HomeCategoryAdapter
import com.cantrk.foodappcleanarchitecture.adapter.PopularMealAdapter
import com.cantrk.foodappcleanarchitecture.adapter.RandomMealAdapter
import com.cantrk.foodappcleanarchitecture.databinding.FragmentNavHomeBinding
import com.cantrk.foodappcleanarchitecture.dataclass.Category
import com.cantrk.foodappcleanarchitecture.dataclass.RandomMeal
import com.cantrk.foodappcleanarchitecture.viewmodel.GetCategoriesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NavHomeFragment : BaseFragment<FragmentNavHomeBinding>(FragmentNavHomeBinding::inflate) {

    private val viewModel : GetCategoriesViewModel by viewModels()
    private lateinit var mealAdapter: HomeCategoryAdapter
    private lateinit var randomMealAdapter: RandomMealAdapter
    private lateinit var popularMealAdapter: PopularMealAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setItemList()
    }

    private fun setCategoryAdapter(categoriesState: List<Category>){
        mealAdapter= HomeCategoryAdapter()
        binding.apply {
            categoryMealRecyclerView.adapter=mealAdapter
            categoryMealRecyclerView.set3DItem(true)
            categoryMealRecyclerView.setAlpha(true)
            categoryMealRecyclerView.setInfinite(true)
        }
         mealAdapter.setMealList(categoriesState)

        mealAdapter.clickCategoryItem = {
            viewModel.setCategoryListItem(it)
            val action= NavHomeFragmentDirections.actionNavHomeFragmentToCategoryListItemFragment()
            findNavController().navigate(action)
        }
    }

    private fun setRandomMealAdapter(list : List<RandomMeal>){
        randomMealAdapter= RandomMealAdapter()
        binding.apply {
            randomMeal.adapter=randomMealAdapter
            randomMeal.layoutManager=LinearLayoutManager(requireContext())
        }
        randomMealAdapter.setMealList(list)

        randomMealAdapter.itemClickListener(object : RandomMealAdapter.SetItemClickListener{
            override fun itemId(itemId: String) {
                if (itemId.isNotEmpty()){
                    viewModel.setMealSavedItemData(itemId)
                    val action= NavHomeFragmentDirections.actionHomeFragmentToMealDetailFragment2()
                    findNavController().navigate(action)
                }
            }

        })
    }

    private fun setPopularMealAdapter(list : List<RandomMeal>){
        popularMealAdapter=PopularMealAdapter()
        binding.apply {
            getPopularMeals.adapter=popularMealAdapter
            getPopularMeals.layoutManager=LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
        popularMealAdapter.setPopularMealList(list)

        popularMealAdapter.setOnClickPopulerMealItem = {
            if (!it.equals("")){
                viewModel.setMealSavedItemData(it.idMeal)
                val action= NavHomeFragmentDirections.actionHomeFragmentToMealDetailFragment2()
                findNavController().navigate(action)
            }
        }
    }

    private fun setItemList(){
        //lifecyclescopelaunch işlemi içerisinde bulunan suspend durumları sırasıyla başlatır. !! ve ilk collect latest
        // çalıştıktan sonra diğerleri çalışmıyor.

        with(viewModel){
        viewModelScope.launch {
            combinedFlow.collectLatest {(categoryState, randomMealState,popularMealState) ->
                categoryState.category?.let { setCategoryAdapter(it) }
                randomMealState.category?.let { setRandomMealAdapter(it) }
                popularMealState.category?.let { setPopularMealAdapter(it)  }
                if ((categoryState.category?.isNotEmpty() == true
                            && randomMealState.category?.isNotEmpty() == true
                            ) ){
                            binding.progressBar.isVisible=false
                            binding.scrollView2.isVisible=true
                    }else{
                        binding.progressBar.isVisible=true
                        binding.scrollView2.isVisible=false
                    }
                }
            }
        }
    }
}