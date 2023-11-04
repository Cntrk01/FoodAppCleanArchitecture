package com.cantrk.foodappcleanarchitecture.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cantrk.foodappcleanarchitecture.Resource
import com.cantrk.foodappcleanarchitecture.states.MealState
import com.cantrk.foodappcleanarchitecture.usecase.GetMealsByCategoryItemListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryListItemViewModel @Inject constructor(
    private val getMealsByCategoryItemListUseCase: GetMealsByCategoryItemListUseCase
): ViewModel() {
    private var _listMealData = MutableStateFlow(MealState())
    val listMealData: StateFlow<MealState> get() = _listMealData

    companion object{
        var categoryName=""
    }
    init {
        getCategoryListMeal(categoryName)
    }
    private fun getCategoryListMeal(categoryNames:String) = viewModelScope.launch{
        getMealsByCategoryItemListUseCase.getMealCategoryItem(categoryNames).collectLatest {
            when(it){
                is Resource.Success->{
                    it.data?.let {response->
                        _listMealData.value= MealState(category = response.meals)
                    }
                }
                is Resource.Loading->{
                    MealState(isLoading = true)
                }
                is Resource.Error->{
                    MealState(error = it.message ?: "Error")
                }
            }
        }
    }
}