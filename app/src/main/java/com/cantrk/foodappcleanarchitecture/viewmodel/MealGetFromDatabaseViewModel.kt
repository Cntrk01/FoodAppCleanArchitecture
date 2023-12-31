package com.cantrk.foodappcleanarchitecture.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cantrk.foodappcleanarchitecture.dataclass.FoodSaveEntity
import com.cantrk.foodappcleanarchitecture.states.FoodSaveState
import com.cantrk.foodappcleanarchitecture.usecase.GetMealFromRoomUseCase
import com.cantrk.foodappcleanarchitecture.util.Response
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

//    private var _getMealClickedItemDetail = MutableStateFlow(FoodSaveState())
//    val getMealClickedItemDetail : StateFlow<FoodSaveState> get() = _getMealClickedItemDetail

    private var _deletedMeal = MutableStateFlow(FoodSaveState())

    private var _addMeal = MutableStateFlow(FoodSaveState())

    fun addMeal(food: FoodSaveEntity) = viewModelScope.launch {
        getMealFromRoomUseCase.addMeal(food).collectLatest {
            when(it){
                is Response.Success->{
                    _addMeal.value= FoodSaveState(isAdded = true)
                }
                is Response.Loading->{
                    FoodSaveState(isLoading = true)
                }
                is Response.Error->{
                    FoodSaveState(error = it.message ?: "Error")
                }
            }
        }
    }

    fun getMealClickedItem(foodId:String) = viewModelScope.launch {
        getMealFromRoomUseCase.getMealClickedItem(foodId).collectLatest {
            when(it){
                is Response.Success->{
                    _getMealClickedItem.value= FoodSaveState(isHave =true, data = it.data)
                }
                is Response.Loading->{
                    FoodSaveState(isLoading = true)
                }
                is Response.Error->{
                    FoodSaveState(error = it.message ?: "Error")
                }
            }
        }
    }

    //bu fonksyion da Int değer döndürdüğüm için sadece true false mantıgında çalışıyordu verileri almnak için üstteki fonksiyonu yazdım
//    private fun getMealClickedItemData(foodId:String)=viewModelScope.launch {
//        getMealFromRoomUseCase.getMealClickedItemData(foodId = foodId).collectLatest {
//            when(it){
//                is Response.Success->{
//                    _getMealClickedItemDetail.value= FoodSaveState(isHave = true,data = it.data)
//                }
//                is Response.Loading->{
//                    FoodSaveState(isLoading = true)
//                }
//                is Response.Error->{
//                    FoodSaveState(error = it.message ?: "Error")
//                }
//            }
//        }
//    }

    fun getAllMeal()=viewModelScope.launch{
        getMealFromRoomUseCase.getAllMeal().collectLatest {
            when(it){
                is Response.Success->{
                    _getAllMeal.value= FoodSaveState(getRoomList = it.data)
                }
                is Response.Loading->{
                    FoodSaveState(isLoading = true)
                }
                is Response.Error->{
                    FoodSaveState(error = it.message ?: "Error")
                }
            }
        }
    }

    fun deleteMeal(foodId: String) = viewModelScope.launch {
        getMealFromRoomUseCase.deleteMeal(foodId).collectLatest {
            when(it){
                is Response.Success->{
                    _deletedMeal.value= FoodSaveState(isDeleted =true)
                }
                is Response.Loading->{
                    FoodSaveState(isLoading = true)
                }
                is Response.Error->{
                    FoodSaveState(error = it.message ?: "Error")
                }
            }
        }
    }

    fun setDetailId(detailId:String){
        MealDetailViewModel.MEAL_ID=detailId
    }
}