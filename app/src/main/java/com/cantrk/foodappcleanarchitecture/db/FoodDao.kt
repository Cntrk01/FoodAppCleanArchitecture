package com.cantrk.foodappcleanarchitecture.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cantrk.foodappcleanarchitecture.dataclass.FoodSaveEntity

@Dao
interface FoodDao {
    @Query("SELECT*FROM food_table")
    fun getAllFood() : FoodSaveEntity

    @Query("SELECT*FROM food_table WHERE food_table.id=:foodId")
    suspend fun getMealClickedItem(foodId:Int) : FoodSaveEntity

    @Delete
    suspend fun deleteMeal(food:FoodSaveEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMeal(food: FoodSaveEntity)
}