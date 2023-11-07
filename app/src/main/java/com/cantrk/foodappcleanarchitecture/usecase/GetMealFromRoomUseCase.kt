package com.cantrk.foodappcleanarchitecture.usecase

import com.cantrk.foodappcleanarchitecture.dataclass.FoodSaveEntity
import com.cantrk.foodappcleanarchitecture.repositoryimpl.FoodDatabaseImpl
import javax.inject.Inject

class GetMealFromRoomUseCase @Inject constructor(private val foodDatabaseImpl: FoodDatabaseImpl) {

    fun getAllMeal()  = foodDatabaseImpl.getAllFood()

    suspend fun  getMealClickedItem(foodId:String) = foodDatabaseImpl.getMealClickedItem(clickFoodId=foodId)

    suspend fun deleteMeal(deletedFoodId: String) = foodDatabaseImpl.deleteMeal(deletedFoodId = deletedFoodId)

    suspend fun addMeal(food: FoodSaveEntity) = foodDatabaseImpl.addMeal(addFood= food)

    //suspend fun getMealClickedItemData(foodId: String)=foodDatabaseImpl.getMealClickedItemData(foodId=foodId)
}