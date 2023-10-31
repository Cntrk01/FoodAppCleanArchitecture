package com.cantrk.foodappcleanarchitecture.usecase

import com.cantrk.foodappcleanarchitecture.Resource
import com.cantrk.foodappcleanarchitecture.dataclass.MealsResponse
import com.cantrk.foodappcleanarchitecture.repositoryimpl.FoodRepositoryImpl
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMealsByCategoryUseCase @Inject constructor(private val repository : FoodRepositoryImpl) {
    suspend fun getMealsByCategory(category: String): Flow<Resource<MealsResponse>>
    = repository.getMealsByCategory(category = category)
}