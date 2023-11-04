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

    fun getAllFood() : Flow<Resource<FoodSaveEntity>> {
        return flow {
            try {
                emit(Resource.Loading())
                val getData=foodDatabase.getAllFood()
                emit(Resource.Success(getData))
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

    suspend fun getMealClickedItem(clickFoodId:Int) : Flow<Resource<FoodSaveEntity>>{
        return flow {
            try {
                emit(Resource.Loading())
                val getMealItem=foodDatabase.getMealClickedItem(foodId = clickFoodId)
                emit(Resource.Success(getMealItem))
            }catch (e:Exception){
                emit(Resource.Error(message = e.message ?: "Exception"))
            }catch (e: SQLiteDatabaseLockedException) {
                emit(Resource.Error(message = e.message ?: "Locked Database"))
            }catch (e: SQLiteConstraintException) {
                emit(Resource.Error(message = e.message ?: "Constraint Database"))
            }
        }
    }

    suspend fun deleteMeal(deleteFood:FoodSaveEntity) : Flow<Resource<Unit>>{
        return flow {
            try {
                emit(Resource.Loading())
                val deleteMealItem=foodDatabase.deleteMeal(food = deleteFood)
                emit(Resource.Success(data = deleteMealItem))
            }catch (e:Exception){
                emit(Resource.Error(message = e.message ?: "Exception"))
            }catch (e: SQLiteDatabaseLockedException) {
                emit(Resource.Error(message = e.message ?: "Locked Database"))
            }catch (e: SQLiteConstraintException) {
                emit(Resource.Error(message = e.message ?: "Constraint Database"))
            }
        }
    }

    suspend fun addMeal(addFood: FoodSaveEntity) : Flow<Resource<Unit>>{
        return flow {
            try {
                emit(Resource.Loading())
                val addFoodItem=foodDatabase.addMeal(food = addFood)
                emit(Resource.Success(data = addFoodItem))
            }catch (e:Exception){
                emit(Resource.Error(message = e.message ?: "Exception"))
            }catch (e: SQLiteDatabaseLockedException) {
                emit(Resource.Error(message = e.message ?: "Locked Database"))
            }catch (e: SQLiteConstraintException) {
                emit(Resource.Error(message = e.message ?: "Constraint Database"))
            }
        }
    }

}