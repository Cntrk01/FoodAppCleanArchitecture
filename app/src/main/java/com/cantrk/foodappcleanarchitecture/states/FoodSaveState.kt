package com.cantrk.foodappcleanarchitecture.states

import com.cantrk.foodappcleanarchitecture.dataclass.FoodSaveEntity

data class FoodSaveState(
    val isLoading : Boolean ?=false,
    val data : FoodSaveEntity ?= null,
    val error : String ?=null,
    val isDeleted : Boolean ?=false,
    val isAdded : Boolean ?=false,
    val isHave : Boolean ?=false,
    val getRoomList : List<FoodSaveEntity> ?=null
    )