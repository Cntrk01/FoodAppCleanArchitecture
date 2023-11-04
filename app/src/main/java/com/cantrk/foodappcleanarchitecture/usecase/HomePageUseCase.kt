package com.cantrk.foodappcleanarchitecture.usecase

import com.cantrk.foodappcleanarchitecture.util.Resource
import com.cantrk.foodappcleanarchitecture.dataclass.CategoryResponse
import com.cantrk.foodappcleanarchitecture.dataclass.RandomResponse
import com.cantrk.foodappcleanarchitecture.repositoryimpl.FoodRepositoryImpl
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HomePageUseCase @Inject constructor(private val repository : FoodRepositoryImpl) {
    suspend fun getCategories(): Flow<Resource<CategoryResponse>> = repository.getCategories()
    suspend fun getMealByName(s: String): Flow<Resource<RandomResponse>> = repository.getMealByName(s=s)
    suspend fun getRandomMeal(): Flow<Resource<RandomResponse>> = repository.getRandomMeal()
}