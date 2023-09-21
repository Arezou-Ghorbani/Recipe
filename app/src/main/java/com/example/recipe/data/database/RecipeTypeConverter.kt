package com.example.recipe.data.database

import androidx.room.TypeConverter
import com.example.recipe.models.recipes.ResponseRecipes
import com.google.gson.Gson

/**Created by Arezou-Ghorbani on 20,September,2023,ArezouGhorbaniii@gmail.com**/
class RecipeTypeConverter {
    private var gson = Gson()

    @TypeConverter
    fun recipeToJson(recipes: ResponseRecipes): String {
        return gson.toJson(recipes)
    }
    @TypeConverter
    fun jsonToRecipe(json: String): ResponseRecipes {
        return gson.fromJson(json, ResponseRecipes::class.java)
    }
}