package com.cantrk.foodappcleanarchitecture.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cantrk.foodappcleanarchitecture.dataclass.FoodSaveEntity
import com.cantrk.foodappcleanarchitecture.states.FoodSaveState

@Dao
interface FoodDao {
    @Query("SELECT*FROM food_table")
    fun getAllFood() : FoodSaveEntity

    @Query("SELECT count(*) FROM food_table WHERE food_table.mealId=:foodId")
    suspend fun getMealClickedItem(foodId:String) : Int

    @Query("DELETE FROM food_table WHERE food_table.mealId=:foodId")
    suspend fun deleteMeal(foodId:String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMeal(food: FoodSaveEntity)
}