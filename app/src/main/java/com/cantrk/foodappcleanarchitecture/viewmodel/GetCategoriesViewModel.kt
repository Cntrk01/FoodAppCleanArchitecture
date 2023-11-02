package com.cantrk.foodappcleanarchitecture.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cantrk.foodappcleanarchitecture.Resource
import com.cantrk.foodappcleanarchitecture.states.CategoriesState
import com.cantrk.foodappcleanarchitecture.states.RandomMealState
import com.cantrk.foodappcleanarchitecture.usecase.HomePageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GetCategoriesViewModel @Inject constructor(private val useCase: HomePageUseCase, ) : ViewModel() {
    private val _categoryResponse = MutableStateFlow(CategoriesState())
    private val categoryResponse: StateFlow<CategoriesState> get() = _categoryResponse

    private val _randomMealResponse = MutableStateFlow(RandomMealState())
    private val randomMeal: StateFlow<RandomMealState> get() = _randomMealResponse

    private val _populerItemResponse = MutableStateFlow(RandomMealState())
    private val populerItemResponse: StateFlow<RandomMealState> get() = _populerItemResponse

    val combinedFlow: Flow<Triple<CategoriesState, RandomMealState, RandomMealState>> = categoryResponse
        .combine(randomMeal) { categoryState, randomMealState ->
            Pair(categoryState, randomMealState)
        }
        .combine(populerItemResponse) { pair, popularItem ->
            Triple(pair.first, pair.second, popularItem)
        }

    init {
        getCategories()
        getRandomMeal()
        getMealByName("beef")
    }
    private fun getCategories() = viewModelScope.launch(Dispatchers.IO) {
        useCase.getCategories().collectLatest { resource ->
            when(resource){
                is Resource.Success->{
                    resource.data?.let {response->
                        _categoryResponse.value= CategoriesState(category = response.categories)
                    }
                }
                is Resource.Loading->{
                    CategoriesState(isLoading = true)
                }
                is Resource.Error->{
                    CategoriesState(error = resource.message ?: "Error")
                }
            }
        }
    }

    private fun getRandomMeal() = viewModelScope.launch {
        useCase.getRandomMeal().collectLatest { resource ->
            when(resource){
                is Resource.Success->{
                    resource.data?.let {response->
                        _randomMealResponse.value= RandomMealState(category = response.meals)
                    }
                }
                is Resource.Loading->{
                    RandomMealState(isLoading = true)
                }
                is Resource.Error->{
                    RandomMealState(error = resource.message ?: "Error")
                }
            }
        }
    }

    private fun getMealByName(name:String)=viewModelScope.launch {
        useCase.getMealByName(name).collectLatest {
            when(it){
                is Resource.Success->{
                    it.data?.let {response->
                        _populerItemResponse.value= RandomMealState(category = response.meals)
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

    fun setMealSavedItemData(data:String){
        MealDetailViewModel.USER_KEY = data
    }
}