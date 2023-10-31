package com.cantrk.foodappcleanarchitecture.repositoryimpl

import com.cantrk.foodappcleanarchitecture.Resource
import com.cantrk.foodappcleanarchitecture.dataclass.CategoryResponse
import com.cantrk.foodappcleanarchitecture.dataclass.MealsResponse
import com.cantrk.foodappcleanarchitecture.dataclass.RandomResponse
import com.cantrk.foodappcleanarchitecture.network.FoodApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class FoodRepositoryImpl @Inject constructor(private val api: FoodApi) : FoodApi {

    override suspend fun getCategories(): Flow<Resource<CategoryResponse>> =
        flow {
            try {
                emit(Resource.Loading())
                val getData=api.getCategories()
                getData.onEach {
                    if (it.data != null){
                        emit(Resource.Success(it.data))
                        println(it.data)
                    }
                }
            }catch (e:Exception){
                emit(Resource.Error(e.message.toString()))
            }catch (e:HttpException){
                emit(Resource.Error(message = e.localizedMessage ?: "Error"))
            }catch (e:IOException){
                emit(Resource.Error("No Internet Connection"))
            }
    }

    override suspend fun getMealsByCategory(category: String): Flow<Resource<MealsResponse>> {
        return flow {
            try {
                emit(Resource.Loading())
                val getMealsByCategory=api.getMealsByCategory(category)
                getMealsByCategory.onEach {
                    if (it.data !=null){
                        emit(Resource.Success(it.data))
                    }
                }

            }catch (e:Exception){
                emit(Resource.Error(e.message.toString()))
            }catch (e:HttpException){
                emit(Resource.Error(message = e.localizedMessage ?: "Error"))
            }catch (e:IOException){
                emit(Resource.Error("No Internet Connection"))
            }
        }
    }

    override suspend fun getRandomMeal(): Flow<Resource<RandomResponse>> {
        return flow {
            try {
                emit(Resource.Loading())
                val getRandomMeal=api.getRandomMeal()
                getRandomMeal.onEach {
                    if (it.data != null){
                        emit(Resource.Success(it.data))
                    }
                }
            }catch (e:Exception){
                emit(Resource.Error(e.message.toString()))
            }catch (e:HttpException){
                emit(Resource.Error(message = e.localizedMessage ?: "Error"))
            }catch (e:IOException){
                emit(Resource.Error("No Internet Connection"))
            }
        }
    }

    override suspend fun getMealById(id: String): Flow<Resource<RandomResponse>> {
        return flow {
            try {
                emit(Resource.Loading())
                val getMealWithId=api.getMealById(id)
                getMealWithId.onEach {
                    if (it.data != null){
                        emit(Resource.Success(it.data))
                    }
                }
            }catch (e:Exception){
                emit(Resource.Error(e.message.toString()))
            }catch (e:HttpException){
                emit(Resource.Error(message = e.localizedMessage ?: "Error"))
            }catch (e:IOException){
                emit(Resource.Error("No Internet Connection"))
            }
        }
    }

    override suspend fun getMealByName(s: String): Flow<Resource<RandomResponse>> {
        return flow {
            try {
                emit(Resource.Loading())
                val getMealByName=api.getMealByName(s)
                getMealByName.onEach {
                    if (it.data != null){
                        emit(Resource.Success(it.data))
                    }
                }
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