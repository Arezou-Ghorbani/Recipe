package com.example.recipe.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.recipe.data.entity.DetailEntity
import com.example.recipe.data.entity.FavoriteEntity
import com.example.recipe.data.entity.RecipeEntity

/**Created by Arezou-Ghorbani on 20,September,2023,ArezouGhorbaniii@gmail.com**/
@Database(entities = [RecipeEntity::class, DetailEntity::class, FavoriteEntity::class], version = 3, exportSchema = false)
@TypeConverters(RecipeTypeConverter::class)
abstract class RecipeAppDatabase:RoomDatabase() {
  abstract fun dao():RecipeAppDao
}