package com.cantrk.foodappcleanarchitecture.states

import com.cantrk.foodappcleanarchitecture.dataclass.Category
import com.cantrk.foodappcleanarchitecture.dataclass.Meal

data class MealState(
    val isLoading : Boolean=false,
    val category : List<Meal> ?= null,
    val error : String = "",
)