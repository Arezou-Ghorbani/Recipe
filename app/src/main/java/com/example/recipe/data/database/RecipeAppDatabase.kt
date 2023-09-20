package com.example.recipe.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters

/**Created by Arezou-Ghorbani on 20,September,2023,ArezouGhorbaniii@gmail.com**/
@Database(entities = [RecipeEntity::class], version = 1, exportSchema = false)
@TypeConverters(RecipeTypeConverter::class)
abstract class RecipeAppDatabase:RoomDatabase() {
  abstract fun dao():RecipeAppDao
}