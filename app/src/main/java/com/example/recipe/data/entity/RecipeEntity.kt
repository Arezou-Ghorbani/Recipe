package com.example.recipe.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.recipe.models.recipes.ResponseRecipes
import com.example.recipe.utils.Constants


/**Created by Arezou-Ghorbani on 12,October,2023,Arezoughorbaniii@gmail.com**/
@Entity(tableName = Constants.RECIPE_TABLE_NAME)
data class RecipeEntity(
    @PrimaryKey(autoGenerate = false)
    var id: Int = 0,
    var response: ResponseRecipes
)