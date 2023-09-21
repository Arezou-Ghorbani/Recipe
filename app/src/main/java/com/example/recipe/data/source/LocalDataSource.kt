package com.example.recipe.data.source

import com.example.recipe.data.database.RecipeAppDao
import com.example.recipe.data.database.RecipeEntity
import javax.inject.Inject

/**Created by Arezou-Ghorbani on 21,September,2023,ArezouGhorbaniii@gmail.com**/
class LocalDataSource @Inject constructor(private val dao: RecipeAppDao) {
    //    Recipe
    suspend fun saveRecipes(entity: RecipeEntity) = dao.saveRecipe(entity)
}