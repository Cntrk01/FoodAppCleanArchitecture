package com.cantrk.foodappcleanarchitecture.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cantrk.foodappcleanarchitecture.dataclass.FoodSaveEntity

@Database(entities = [FoodSaveEntity::class], version = 3, exportSchema = false)
abstract class FoodDatabase : RoomDatabase() {
    abstract fun foodDao() : FoodDao
}