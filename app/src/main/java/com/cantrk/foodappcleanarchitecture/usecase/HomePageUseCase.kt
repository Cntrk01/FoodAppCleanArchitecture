package com.cantrk.foodappcleanarchitecture.usecase

import com.cantrk.foodappcleanarchitecture.util.Response
import com.cantrk.foodappcleanarchitecture.dataclass.CategoryResponse
import com.cantrk.foodappcleanarchitecture.dataclass.RandomResponse
import com.cantrk.foodappcleanarchitecture.repositoryimpl.FoodRepositoryImpl
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HomePageUseCase @Inject constructor(private val repository : FoodRepositoryImpl) {
    suspend fun getCategories(): Flow<Response<CategoryResponse>> = repository.getCategories()
    suspend fun getMealByName(s: String): Flow<Response<RandomResponse>> = repository.getMealByName(s=s)
    suspend fun getRandomMeal(): Flow<Response<RandomResponse>> = repository.getRandomMeal()
}