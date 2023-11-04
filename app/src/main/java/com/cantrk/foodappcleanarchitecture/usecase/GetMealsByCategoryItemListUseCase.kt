package com.cantrk.foodappcleanarchitecture.usecase

import com.cantrk.foodappcleanarchitecture.util.Resource
import com.cantrk.foodappcleanarchitecture.dataclass.MealsResponse
import com.cantrk.foodappcleanarchitecture.repositoryimpl.FoodRepositoryImpl
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMealsByCategoryItemListUseCase @Inject constructor(private val repository : FoodRepositoryImpl) {
    suspend fun getMealCategoryItem(categoryName: String): Flow<Resource<MealsResponse>>
    = repository.getMealsByCategoryGetItem(s = categoryName)
}