package com.cantrk.foodappcleanarchitecture.dataclass

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "food_table")
data class FoodSaveEntity(
    @PrimaryKey(autoGenerate = true)
    val id : Int ?=null,
    val image:String,
    val title:String,
    val category:String,
    val location:String,
    val desc:String,
    val youtubeUrl:String,
)
