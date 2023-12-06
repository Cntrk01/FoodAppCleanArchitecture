package com.cantrk.foodappcleanarchitecture.network

import com.cantrk.foodappcleanarchitecture.dataclass.CategoryResponse
import com.cantrk.foodappcleanarchitecture.dataclass.MealsResponse
import com.cantrk.foodappcleanarchitecture.dataclass.RandomResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface FoodApi {
    @GET("categories.php")
    suspend fun getCategories(): CategoryResponse

    @GET ("random.php")
    suspend fun getRandomMeal():RandomResponse

    @GET("lookup.php?")
    suspend fun getMealById(@Query("i") id:String):RandomResponse

    @GET("search.php?")
    suspend fun getMealByName(@Query("s") s:String):RandomResponse
    //tıklanan kategorilerden veri getirme.normalde i parametresi ile alıyordum fakat boş gelenler vardı dökümanda c parametresini görüp denedim bütün kategoriler dolu geliyor.
    @GET("filter.php?")
    suspend fun getMealsByCategoryGetItem(@Query("c") category:String):MealsResponse
}