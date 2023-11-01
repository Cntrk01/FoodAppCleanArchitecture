package com.cantrk.foodappcleanarchitecture.states

import com.cantrk.foodappcleanarchitecture.dataclass.RandomMeal

data class RandomMealState(
    val isLoading : Boolean=false,
    var category : List<RandomMeal> ?= null,
    val error : String = "",
)