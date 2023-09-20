package com.example.recipe.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.recipe.models.recipes.ResponseRecipes
import com.example.recipe.utils.Constant

/**Created by Arezou-Ghorbani on 19,September,2023,ArezouGhorbaniii@gmail.com**/
@Entity(tableName = Constant.RECIPE_TABLE_NAME)
data class RecipeEntity(
    @PrimaryKey(autoGenerate = false)
    var id: Int = 0,
    var responseRecipes: ResponseRecipes
)
//    all response which is using in main recipe fragment completely is saving
