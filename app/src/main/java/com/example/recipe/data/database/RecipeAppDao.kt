package com.example.recipe.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.recipe.utils.Constant
import kotlinx.coroutines.flow.Flow

/**Created by Arezou-Ghorbani on 20,September,2023,ArezouGhorbaniii@gmail.com**/
@Dao
interface RecipeAppDao {
    //    Recipe
    @Insert(onConflict = REPLACE)
    suspend fun saveRecipe(entity: RecipeEntity)
    @Query("SELECT * FROM ${Constant.RECIPE_DATABASE_NAME} ORDER BY ID ASC")
    fun loadRecipes(): Flow<List<RecipeEntity>>
}