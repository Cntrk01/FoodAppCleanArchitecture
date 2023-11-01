package com.cantrk.foodappcleanarchitecture.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.viewModelScope
import com.cantrk.foodappcleanarchitecture.BaseFragment
import com.cantrk.foodappcleanarchitecture.adapter.CategoryAdapter
import com.cantrk.foodappcleanarchitecture.databinding.FragmentHomeBinding
import com.cantrk.foodappcleanarchitecture.dataclass.Category
import com.cantrk.foodappcleanarchitecture.viewmodel.GetCategoriesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val viewModel : GetCategoriesViewModel by activityViewModels()
    private lateinit var mealAdapter: CategoryAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //1.kısım random meal
        //2.kısım most request meal o da  @GET("categories.php") ye beef parametresini vermiş ve ekranda göstermiş
        //3.kısım zaten kategoriler
        setItemList()

    }

    private fun setCategoryAdapter(categoriesState: List<Category>){
        mealAdapter= CategoryAdapter()
        binding.apply {
            categoryMealRecyclerView.adapter=mealAdapter
            categoryMealRecyclerView.set3DItem(true)
            categoryMealRecyclerView.setAlpha(true)
            categoryMealRecyclerView.setInfinite(true)
        }
         mealAdapter.setMealList(categoriesState)
    }

    private fun setCategoryAdapter(){

    }

    private fun setItemList(){
        viewModel.categoryState.category?.let {   }
        with(viewModel){
        viewModelScope.launch {
            combinedFlow.collectLatest {(categoryState, randomMealState,popularMealState) ->
                categoryState.category?.let { setCategoryAdapter(it) }
                randomMealState.category?.let {  }
                Log.e("randomState",randomMealState.category.toString())
                Log.e("randomState2",popularMealState.category.toString())
                }
            }
        }
        //lifecyclescopelaunch işlemi içerisinde bulunan suspend durumları sırasıyla başlatır. !! ve ilk collect latest çalıştıktan sonra diğerleri çalışmıyor.
    }
}