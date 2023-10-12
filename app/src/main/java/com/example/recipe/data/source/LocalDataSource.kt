package com.example.recipe.data.source

import com.example.recipe.data.entity.FavoriteEntity
import com.example.recipe.data.database.RecipeAppDao
import com.example.recipe.data.entity.DetailEntity
import javax.inject.Inject

/**Created by Arezou-Ghorbani on 21,September,2023,ArezouGhorbaniii@gmail.com**/
class LocalDataSource @Inject constructor(private val dao: RecipeAppDao) {
    //    Recipe
    suspend fun saveRecipes(entity: RecipeEntity) = dao.saveRecipe(entity)
    fun loadRecipes() = dao.loadRecipes()
    //Detail
    suspend fun saveDetail(entity: DetailEntity) = dao.saveDetail(entity)
    fun loadDetail(id: Int) = dao.loadDetail(id)
    fun existsDetail(id: Int) = dao.existsDetail(id)

    //Favorite
    suspend fun saveFavorite(entity: FavoriteEntity) = dao.saveFavorite(entity)
    suspend fun deleteFavorite(entity: FavoriteEntity) = dao.deleteFavorite(entity)
    fun loadFavorites() = dao.loadFavorites()
    fun existsFavorite(id: Int) = dao.existsFavorite(id)
}