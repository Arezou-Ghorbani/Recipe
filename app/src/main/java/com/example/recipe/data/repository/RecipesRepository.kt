package com.example.recipe.data.repository

import com.example.recipe.data.source.LocalDataSource
import com.example.recipe.data.source.RemoteDataSource
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

/**Created by Arezou-Ghorbani on 13,September,2023,ArezouGhorbaniii@gmail.com**/
@ActivityRetainedScoped
class RecipesRepository @Inject constructor(
    remoteDataSource: RemoteDataSource,
    localDataSource: LocalDataSource
) {
    //    suspend fun getRecipes(map: Map<String, String>) = remote.getRecipes(map)
    val remote = remoteDataSource
    val local = localDataSource

}