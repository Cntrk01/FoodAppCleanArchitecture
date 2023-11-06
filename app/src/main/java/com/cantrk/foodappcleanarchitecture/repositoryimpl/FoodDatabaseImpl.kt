package com.cantrk.foodappcleanarchitecture.repositoryimpl

import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteDatabaseLockedException
import com.cantrk.foodappcleanarchitecture.dataclass.FoodSaveEntity
import com.cantrk.foodappcleanarchitecture.db.FoodDao
import com.cantrk.foodappcleanarchitecture.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class FoodDatabaseImpl @Inject constructor(private val foodDatabase: FoodDao) {
    suspend fun addMeal(addFood: FoodSaveEntity): Flow<Resource<Boolean>> {
        return flow {
            try {
                emit(Resource.Loading())
                foodDatabase.addMeal(addFood)
                emit(Resource.Success(data = true))
            } catch (e: Exception) {
                emit(Resource.Error(message = e.message ?: "Exception", data = false))
            } catch (e: SQLiteDatabaseLockedException) {
                emit(Resource.Error(message = e.message ?: "Locked Database", data = false))
            } catch (e: SQLiteConstraintException) {
                emit(Resource.Error(message = e.message ?: "Constraint Database", data = false))
            }
        }
    }
    suspend fun getMealClickedItem(clickFoodId:String) : Flow<Resource<String>>{
        return flow {
            try {
                emit(Resource.Loading())
                val getMealItem=foodDatabase.getMealClickedItem(foodId = clickFoodId)
                if (getMealItem>0){
                    emit(Resource.Success(getMealItem.toString()))
                }
            }catch (e:Exception){
                emit(Resource.Error(message = e.message ?: "Exception"))
            }catch (e: SQLiteDatabaseLockedException) {
                emit(Resource.Error(message = e.message ?: "Locked Database"))
            }catch (e: SQLiteConstraintException) {
                emit(Resource.Error(message = e.message ?: "Constraint Database"))
            }
        }
    }

    suspend fun getMealClickedItemData(foodId: String) : Flow<Resource<FoodSaveEntity>>{
        return flow {
            try {
                emit(Resource.Loading())
                val getMeal=foodDatabase.getMealClickedItemData(foodId)
                if (!getMeal?.mealId.equals("")){
                    emit(Resource.Success(getMeal!!))
                }
            }catch (e:Exception){
                emit(Resource.Error(message = e.message ?: "Exception"))
            }catch (e: SQLiteDatabaseLockedException) {
                emit(Resource.Error(message = e.message ?: "Locked Database"))
            }catch (e: SQLiteConstraintException) {
                emit(Resource.Error(message = e.message ?: "Constraint Database"))
            }
        }
    }
    fun getAllFood() : Flow<Resource<List<FoodSaveEntity>>> {
        return flow {
            try {
                emit(Resource.Loading())
                val foodData = foodDatabase.getAllFood()

                foodData.collect{
                    emit(Resource.Success(it))
                }

            }catch (e:Exception){
                emit(Resource.Error(message = e.message ?: "Exception"))
            }catch (e: SQLiteDatabaseLockedException) {
                // SQLite kilitlenme hatası işleme
                emit(Resource.Error(message = e.message ?: "Locked Database"))
            }catch (e: SQLiteConstraintException) {
                // SQLite kısıtlama hatası işleme
                emit(Resource.Error(message = e.message ?: "Constraint Database"))
            }
        }
    }

    suspend fun deleteMeal(deletedFoodId:String) : Flow<Resource<Boolean>>{
        return flow {
            try {
                emit(Resource.Loading())
                foodDatabase.deleteMeal(foodId = deletedFoodId)
                emit(Resource.Success(data = true))
            }catch (e:Exception){
                emit(Resource.Error(message = e.message ?: "Exception", data = false))
            }catch (e: SQLiteDatabaseLockedException) {
                emit(Resource.Error(message = e.message ?: "Locked Database",data = false))
            }catch (e: SQLiteConstraintException) {
                emit(Resource.Error(message = e.message ?: "Constraint Database",data = false))
            }
        }
    }



}