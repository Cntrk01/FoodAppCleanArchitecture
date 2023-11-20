package com.cantrk.foodappcleanarchitecture.repositoryimpl

import com.cantrk.foodappcleanarchitecture.util.Response
import com.cantrk.foodappcleanarchitecture.dataclass.CategoryResponse
import com.cantrk.foodappcleanarchitecture.dataclass.MealsResponse
import com.cantrk.foodappcleanarchitecture.dataclass.RandomResponse
import com.cantrk.foodappcleanarchitecture.network.FoodApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class FoodRepositoryImpl @Inject constructor(private val api: FoodApi){

    suspend fun getCategories(): Flow<Response<CategoryResponse>> =
        flow {
            try {
                emit(Response.Loading())
                val getData=api.getCategories()
                emit(Response.Success(getData))
            }catch (e:Exception){
                emit(Response.Error(e.message.toString()))
            }catch (e:HttpException){
                emit(Response.Error(message = e.localizedMessage ?: "Error"))
            }catch (e:IOException){
                emit(Response.Error("No Internet Connection"))
            }
    }

    suspend fun getMealsByCategory(category: String): Flow<Response<MealsResponse>> {
        return flow {
            try {
                emit(Response.Loading())
                val getMealsByCategory=api.getMealsByCategory(category)
                emit(Response.Success(getMealsByCategory))
            }catch (e:Exception){
                emit(Response.Error(e.message.toString()))
            }catch (e:HttpException){
                emit(Response.Error(message = e.localizedMessage ?: "Error"))
            }catch (e:IOException){
                emit(Response.Error("No Internet Connection"))
            }
        }
    }

    suspend fun getRandomMeal(): Flow<Response<RandomResponse>> {
        return flow {
            try {
                emit(Response.Loading())
                val data=api.getRandomMeal()
                emit(Response.Success(data))
            }catch (e:Exception){
                emit(Response.Error(e.message.toString()))
            }catch (e:HttpException){
                emit(Response.Error(message = e.localizedMessage ?: "Error"))
            }catch (e:IOException){
                emit(Response.Error("No Internet Connection"))
            }
        }
    }

    suspend fun getMealById(id: String): Flow<Response<RandomResponse>> {
        return flow {
            try {
                emit(Response.Loading())
                val getMealWithId=api.getMealById(id)
                emit(Response.Success(getMealWithId))
            }catch (e:Exception){
                emit(Response.Error(e.message.toString()))
            }catch (e:HttpException){
                emit(Response.Error(message = e.localizedMessage ?: "Error"))
            }catch (e:IOException){
                emit(Response.Error("No Internet Connection"))
            }
        }
    }

    suspend fun getMealByName(s: String): Flow<Response<RandomResponse>> {
        return flow {
            try {
                emit(Response.Loading())
                val getMealByName=api.getMealByName(s)
                emit(Response.Success(getMealByName))
            }catch (e:Exception){
                emit(Response.Error(e.message.toString()))
            }catch (e:HttpException){
                emit(Response.Error(message = e.localizedMessage ?: "Error"))
            }catch (e:IOException){
                emit(Response.Error("No Internet Connection"))
            }
        }
    }

    suspend fun getMealsByCategoryGetItem(s:String) : Flow<Response<MealsResponse>> {
        return flow {
            try {
                emit(Response.Loading())
                val getMealByName=api.getMealsByCategoryGetItem(s)
                emit(Response.Success(getMealByName))
            }catch (e:Exception){
                emit(Response.Error(e.message.toString()))
            }catch (e:HttpException){
                emit(Response.Error(message = e.localizedMessage ?: "Error"))
            }catch (e:IOException){
                emit(Response.Error("No Internet Connection"))
            }
        }
    }
}