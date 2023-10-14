package com.example.recipe.data.database

import androidx.room.TypeConverter
import com.example.recipe.models.detail.ResponseDetail
import com.example.recipe.models.recipes.ResponseRecipes
import com.google.gson.Gson

/**Created by Arezou-Ghorbani on 20,September,2023,ArezouGhorbaniii@gmail.com**/
class RecipeTypeConverter {
    private val gson = Gson()

    @TypeConverter
    fun recipeToJson(recipe: ResponseRecipes): String {
        return gson.toJson(recipe)
    }

    @TypeConverter
    fun stringToRecipe(data: String): ResponseRecipes {
        return gson.fromJson(data, ResponseRecipes::class.java)
    }

    @TypeConverter
    fun detailToJson(recipe: ResponseDetail): String {
        return gson.toJson(recipe)
    }

    @TypeConverter
    fun stringToDetail(data: String): ResponseDetail {
        return gson.fromJson(data, ResponseDetail::class.java)
    }

}