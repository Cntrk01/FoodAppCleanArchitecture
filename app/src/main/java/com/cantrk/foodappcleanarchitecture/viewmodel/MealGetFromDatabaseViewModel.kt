package com.cantrk.foodappcleanarchitecture.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cantrk.foodappcleanarchitecture.dataclass.FoodSaveEntity
import com.cantrk.foodappcleanarchitecture.states.FoodSaveState
import com.cantrk.foodappcleanarchitecture.usecase.GetMealFromRoomUseCase
import com.cantrk.foodappcleanarchitecture.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealGetFromDatabaseViewModel @Inject constructor(private val getMealFromRoomUseCase: GetMealFromRoomUseCase) : ViewModel() {

    private var _getAllMeal = MutableStateFlow(FoodSaveState())
    val getAllMeal : StateFlow<FoodSaveState> get() = _getAllMeal

    private var _getMealClickedItem = MutableStateFlow(FoodSaveState())
    val getMealClickedItem : StateFlow<FoodSaveState> get() = _getMealClickedItem

    private var _deletedMeal = MutableStateFlow(FoodSaveState())
    val deletedMeal: StateFlow<FoodSaveState> get() = _deletedMeal

    private var _addMeal = MutableStateFlow(FoodSaveState())
    val addMeal: StateFlow<FoodSaveState> get() = _addMeal

    fun addMeal(food: FoodSaveEntity) = viewModelScope.launch {
        getMealFromRoomUseCase.addMeal(food).collectLatest {
            when(it){
                is Resource.Success->{
                    _addMeal.value= FoodSaveState(isAdded = true)
                }
                is Resource.Loading->{
                    FoodSaveState(isLoading = true)
                }
                is Resource.Error->{
                    FoodSaveState(error = it.message ?: "Error")
                }
            }
        }
    }

    fun getMealClickedItem(foodId:String) = viewModelScope.launch {
        getMealFromRoomUseCase.getMealClickedItem(foodId).collectLatest {
            when(it){
                is Resource.Success->{
                    _getMealClickedItem.value= FoodSaveState(isHave =true)
                }
                is Resource.Loading->{
                    FoodSaveState(isLoading = true)
                }
                is Resource.Error->{
                    FoodSaveState(error = it.message ?: "Error")

                }
            }
        }
    }

    fun getAllMeal()=viewModelScope.launch{
        getMealFromRoomUseCase.getAllMeal().collectLatest {
            when(it){
                is Resource.Success->{
                    _getAllMeal.value= FoodSaveState(data = it.data)
                }
                is Resource.Loading->{
                    FoodSaveState(isLoading = true)
                }
                is Resource.Error->{
                    FoodSaveState(error = it.message ?: "Error")
                }
            }
        }
    }

    fun deleteMeal(foodId: String) = viewModelScope.launch {
        getMealFromRoomUseCase.deleteMeal(foodId).collectLatest {
            when(it){
                is Resource.Success->{
                    _deletedMeal.value= FoodSaveState(isDeleted =true)
                }
                is Resource.Loading->{
                    FoodSaveState(isLoading = true)
                }
                is Resource.Error->{
                    FoodSaveState(error = it.message ?: "Error")
                }
            }
        }
    }


}