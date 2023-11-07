package com.cantrk.foodappcleanarchitecture.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cantrk.foodappcleanarchitecture.dataclass.FoodSaveEntity
import com.cantrk.foodappcleanarchitecture.states.FoodSaveState
import com.cantrk.foodappcleanarchitecture.usecase.GetMealFromRoomUseCase
import com.cantrk.foodappcleanarchitecture.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
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

//    private var _getMealClickedItemDetail = MutableStateFlow(FoodSaveState())
//    val getMealClickedItemDetail : StateFlow<FoodSaveState> get() = _getMealClickedItemDetail

    private var _deletedMeal = MutableStateFlow(FoodSaveState())

    private var _addMeal = MutableStateFlow(FoodSaveState())

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
                    _getMealClickedItem.value= FoodSaveState(isHave =true, data = it.data)
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

//    private fun getMealClickedItemData(foodId:String)=viewModelScope.launch {
//        getMealFromRoomUseCase.getMealClickedItemData(foodId = foodId).collectLatest {
//            when(it){
//                is Resource.Success->{
//                    _getMealClickedItemDetail.value= FoodSaveState(isHave = true,data = it.data)
//                }
//                is Resource.Loading->{
//                    FoodSaveState(isLoading = true)
//                }
//                is Resource.Error->{
//                    FoodSaveState(error = it.message ?: "Error")
//                }
//            }
//        }
//    }

    fun getAllMeal()=viewModelScope.launch{
        getMealFromRoomUseCase.getAllMeal().collectLatest {
            when(it){
                is Resource.Success->{
                    _getAllMeal.value= FoodSaveState(getRoomList = it.data)
                    //Log.e("roomdata",it.data.toString())
                }
                is Resource.Loading->{
                    FoodSaveState(isLoading = true)
                }
                is Resource.Error->{
                    FoodSaveState(error = it.message ?: "Error")
                    Log.e("errrrrrr",it.message.toString())
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