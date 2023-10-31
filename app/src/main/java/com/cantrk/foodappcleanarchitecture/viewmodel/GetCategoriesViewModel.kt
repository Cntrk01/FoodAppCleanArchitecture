package com.cantrk.foodappcleanarchitecture.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cantrk.foodappcleanarchitecture.Resource
import com.cantrk.foodappcleanarchitecture.Resource.Loading
import com.cantrk.foodappcleanarchitecture.states.CategoriesState
import com.cantrk.foodappcleanarchitecture.usecase.GetCategoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GetCategoriesViewModel @Inject constructor(private val useCase: GetCategoriesUseCase) : ViewModel() {

    private val _categoryResponse = MutableStateFlow(CategoriesState())
    val categoryResponse: StateFlow<CategoriesState> get() = _categoryResponse

    init {
        getCategories()
    }
    private fun getCategories() = viewModelScope.launch {
        useCase.getCategories().collectLatest {
            when(it){
                is Resource.Success->{
                    it.data?.let {response->
                        _categoryResponse.value= CategoriesState(category = response.categories)
                    }
                }
                is Resource.Loading->{
                    CategoriesState(isLoading = true)
                }
                is Resource.Error->{
                    CategoriesState(error = it.message ?: "Error")
                }
            }
        }
    }
}