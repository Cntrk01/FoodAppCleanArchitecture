package com.cantrk.foodappcleanarchitecture.usecase

import com.cantrk.foodappcleanarchitecture.dataclass.FoodSaveEntity
import com.cantrk.foodappcleanarchitecture.repositoryimpl.FoodDatabaseImpl
import javax.inject.Inject

class GetMealFromRoomUseCase @Inject constructor(private val foodDatabaseImpl: FoodDatabaseImpl) {

    fun getAllFood()  = foodDatabaseImpl.getAllFood()

    suspend fun  getMealClickedItem(foodId:Int) = foodDatabaseImpl.getMealClickedItem(clickFoodId=foodId)

    suspend fun deleteMeal(food: FoodSaveEntity) = foodDatabaseImpl.deleteMeal(deleteFood = food)

    suspend fun addMeal(food: FoodSaveEntity) = foodDatabaseImpl.addMeal(addFood=food)
}