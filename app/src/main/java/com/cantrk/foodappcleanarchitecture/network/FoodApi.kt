package com.cantrk.foodappcleanarchitecture.network

import com.cantrk.foodappcleanarchitecture.Resource
import com.cantrk.foodappcleanarchitecture.dataclass.CategoryResponse
import com.cantrk.foodappcleanarchitecture.dataclass.MealsResponse
import com.cantrk.foodappcleanarchitecture.dataclass.RandomResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query

interface FoodApi {
    @GET("categories.php")
    suspend fun getCategories(): CategoryResponse

    @GET("categories.php")
    suspend fun getMealsByCategory(@Query("i") category:String): MealsResponse

    @GET ("random.php")
    suspend fun getRandomMeal():RandomResponse

    @GET("lookup.php?")
    suspend fun getMealById(@Query("i") id:String):RandomResponse

    @GET("search.php?")
    suspend fun getMealByName(@Query("s") s:String):RandomResponse
}