package com.cantrk.foodappcleanarchitecture.states

import com.cantrk.foodappcleanarchitecture.dataclass.Category


data class CategoriesState(
    val isLoading : Boolean=false,
    val category : List<Category> ?= null,
    val error : String = "",
)
