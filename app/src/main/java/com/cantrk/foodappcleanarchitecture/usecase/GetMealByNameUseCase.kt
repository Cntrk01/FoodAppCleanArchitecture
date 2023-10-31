package com.cantrk.foodappcleanarchitecture.usecase

import com.cantrk.foodappcleanarchitecture.Resource
import com.cantrk.foodappcleanarchitecture.dataclass.RandomResponse
import com.cantrk.foodappcleanarchitecture.repositoryimpl.FoodRepositoryImpl
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMealByNameUseCase @Inject constructor(private val repository : FoodRepositoryImpl) {
    suspend fun getMealByName(s: String): Flow<Resource<RandomResponse>> = repository.getMealByName(s=s)
}