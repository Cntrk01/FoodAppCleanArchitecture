package com.cantrk.foodappcleanarchitecture.repositoryimpl

import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteDatabaseLockedException
import com.cantrk.foodappcleanarchitecture.dataclass.FoodSaveEntity
import com.cantrk.foodappcleanarchitecture.db.FoodDao
import com.cantrk.foodappcleanarchitecture.util.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class FoodDatabaseImpl @Inject constructor(private val foodDatabase: FoodDao) {
    suspend fun addMeal(addFood: FoodSaveEntity): Flow<Response<Boolean>> {
        return flow {
            try {
                emit(Response.Loading())
                foodDatabase.addMeal(addFood)
                emit(Response.Success(data = true))
            } catch (e: Exception) {
                emit(Response.Error(message = e.message ?: "Exception", data = false))
            } catch (e: SQLiteDatabaseLockedException) {
                emit(Response.Error(message = e.message ?: "Locked Database", data = false))
            } catch (e: SQLiteConstraintException) {
                emit(Response.Error(message = e.message ?: "Constraint Database", data = false))
            }
        }
    }
    suspend fun getMealClickedItem(clickFoodId:String) : Flow<Response<FoodSaveEntity>>{
        return flow {
            try {
                emit(Response.Loading())
                val getMealItem=foodDatabase.getMealClickedItem(foodId = clickFoodId)
                if (clickFoodId==getMealItem.mealId){
                    emit(Response.Success(getMealItem))
                }
            }catch (e:Exception){
                emit(Response.Error(message = e.message ?: "Exception"))
            }catch (e: SQLiteDatabaseLockedException) {
                emit(Response.Error(message = e.message ?: "Locked Database"))
            }catch (e: SQLiteConstraintException) {
                emit(Response.Error(message = e.message ?: "Constraint Database"))
            }
        }
    }

//    suspend fun getMealClickedItemData(foodId: String) : Flow<Response<FoodSaveEntity>>{
//        return flow {
//            try {
//                emit(Response.Loading())
//                val getMeal=foodDatabase.getMealClickedItemData(foodId)
//                if (!getMeal?.mealId.equals("")){
//                    emit(Response.Success(getMeal!!))
//                }
//            }catch (e:Exception){
//                emit(Response.Error(message = e.message ?: "Exception"))
//            }catch (e: SQLiteDatabaseLockedException) {
//                emit(Response.Error(message = e.message ?: "Locked Database"))
//            }catch (e: SQLiteConstraintException) {
//                emit(Response.Error(message = e.message ?: "Constraint Database"))
//            }
//        }
//    }
    fun getAllFood() : Flow<Response<List<FoodSaveEntity>>> {
        return flow {
            try {
                emit(Response.Loading())
                val foodData = foodDatabase.getAllFood()
                foodData.collect{
                    emit(Response.Success(it))
                }
            }catch (e:Exception){
                emit(Response.Error(message = e.message ?: "Exception"))
            }catch (e: SQLiteDatabaseLockedException) {
                emit(Response.Error(message = e.message ?: "Locked Database"))
            }catch (e: SQLiteConstraintException) {
                emit(Response.Error(message = e.message ?: "Constraint Database"))
            }
        }
    }

    suspend fun deleteMeal(deletedFoodId:String) : Flow<Response<Boolean>>{
        return flow {
            try {
                emit(Response.Loading())
                foodDatabase.deleteMeal(foodId = deletedFoodId)
                emit(Response.Success(data = true))
            }catch (e:Exception){
                emit(Response.Error(message = e.message ?: "Exception", data = false))
            }catch (e: SQLiteDatabaseLockedException) {
                emit(Response.Error(message = e.message ?: "Locked Database",data = false))
            }catch (e: SQLiteConstraintException) {
                emit(Response.Error(message = e.message ?: "Constraint Database",data = false))
            }
        }
    }
}