package com.cantrk.foodappcleanarchitecture.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cantrk.foodappcleanarchitecture.dataclass.FoodSaveEntity


@Dao
interface FoodDao {
    @Query("SELECT*FROM food_table")
    fun getAllFood() : kotlinx.coroutines.flow.Flow<List<FoodSaveEntity>>

    @Query("SELECT count(*) FROM food_table WHERE food_table.mealId=:foodId")
    suspend fun getMealClickedItem(foodId:String) : Int

    @Query("DELETE FROM food_table WHERE food_table.mealId=:foodId")
    suspend fun deleteMeal(foodId:String)

    @Query("SELECT * FROM food_table WHERE food_table.mealId=:foodId")
    suspend fun getMealClickedItemData(foodId: String): FoodSaveEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMeal(food: FoodSaveEntity)
}