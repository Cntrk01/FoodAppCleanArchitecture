package com.cantrk.foodappcleanarchitecture.dataclass

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "food_table")
data class FoodSaveEntity(
    @PrimaryKey(autoGenerate = true)
    val id : Int ?=null,
    val image:String ?=null,
    val title:String ?=null,
    val category:String?=null,
    val location:String?=null,
    val desc:String?=null,
    val youtubeUrl:String?=null,
    val mealId : String ?=null
)
