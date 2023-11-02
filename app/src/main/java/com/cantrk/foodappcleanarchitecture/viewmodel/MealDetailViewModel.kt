package com.cantrk.foodappcleanarchitecture.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cantrk.foodappcleanarchitecture.Resource
import com.cantrk.foodappcleanarchitecture.states.CategoriesState
import com.cantrk.foodappcleanarchitecture.states.RandomMealState
import com.cantrk.foodappcleanarchitecture.usecase.GetMealByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealDetailViewModel @Inject constructor(private val mealByIdUseCase: GetMealByIdUseCase,
   ) : ViewModel() {

    private val _mealDetail= MutableStateFlow(RandomMealState())
    val mealDetail: StateFlow<RandomMealState> get() = _mealDetail

    companion object {
         var USER_KEY = "userId"
    }

    init {
        getMealDetail(getMealId())
    }

    private fun getMealId() : String {
        return USER_KEY
    }

    private fun getMealDetail(mealId:String) =viewModelScope.launch {
        mealByIdUseCase.getMealById(mealId).collectLatest {
            when(it){
                is Resource.Success->{
                    it.data?.let {response->
                        _mealDetail.value= RandomMealState(category = response.meals)
                    }
                }
                is Resource.Loading->{
                    RandomMealState(isLoading = true)
                }
                is Resource.Error->{
                    RandomMealState(error = it.message ?: "Error")
                }
            }
        }
    }
}