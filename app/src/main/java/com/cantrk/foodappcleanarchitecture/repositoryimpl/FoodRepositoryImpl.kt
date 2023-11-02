package com.cantrk.foodappcleanarchitecture.repositoryimpl

import android.util.Log
import com.cantrk.foodappcleanarchitecture.Resource
import com.cantrk.foodappcleanarchitecture.dataclass.CategoryResponse
import com.cantrk.foodappcleanarchitecture.dataclass.MealsResponse
import com.cantrk.foodappcleanarchitecture.dataclass.RandomResponse
import com.cantrk.foodappcleanarchitecture.network.FoodApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class FoodRepositoryImpl @Inject constructor(private val api: FoodApi){

    suspend fun getCategories(): Flow<Resource<CategoryResponse>> =
        flow {
            try {
                emit(Resource.Loading())
                val getData=api.getCategories()
                emit(Resource.Success(getData))
            }catch (e:Exception){
                emit(Resource.Error(e.message.toString()))
            }catch (e:HttpException){
                emit(Resource.Error(message = e.localizedMessage ?: "Error"))
            }catch (e:IOException){
                emit(Resource.Error("No Internet Connection"))
            }
    }

    suspend fun getMealsByCategory(category: String): Flow<Resource<MealsResponse>> {
        return flow {
            try {
                emit(Resource.Loading())
                val getMealsByCategory=api.getMealsByCategory(category)
                emit(Resource.Success(getMealsByCategory))
            }catch (e:Exception){
                emit(Resource.Error(e.message.toString()))
            }catch (e:HttpException){
                emit(Resource.Error(message = e.localizedMessage ?: "Error"))
            }catch (e:IOException){
                emit(Resource.Error("No Internet Connection"))
            }
        }
    }

    suspend fun getRandomMeal(): Flow<Resource<RandomResponse>> {
        return flow {
            try {
                emit(Resource.Loading())
                val data=api.getRandomMeal()
                emit(Resource.Success(data))
            }catch (e:Exception){
                emit(Resource.Error(e.message.toString()))
            }catch (e:HttpException){
                emit(Resource.Error(message = e.localizedMessage ?: "Error"))
            }catch (e:IOException){
                emit(Resource.Error("No Internet Connection"))
            }
        }
    }

    suspend fun getMealById(id: String): Flow<Resource<RandomResponse>> {
        return flow {
            try {
                emit(Resource.Loading())
                val getMealWithId=api.getMealById(id)
                emit(Resource.Success(getMealWithId))
            }catch (e:Exception){
                emit(Resource.Error(e.message.toString()))
            }catch (e:HttpException){
                emit(Resource.Error(message = e.localizedMessage ?: "Error"))
            }catch (e:IOException){
                emit(Resource.Error("No Internet Connection"))
            }
        }
    }

    suspend fun getMealByName(s: String): Flow<Resource<RandomResponse>> {
        return flow {
            try {
                emit(Resource.Loading())
                val getMealByName=api.getMealByName(s)
                emit(Resource.Success(getMealByName))
            }catch (e:Exception){
                emit(Resource.Error(e.message.toString()))
            }catch (e:HttpException){
                emit(Resource.Error(message = e.localizedMessage ?: "Error"))
            }catch (e:IOException){
                emit(Resource.Error("No Internet Connection"))
            }
        }
    }
}