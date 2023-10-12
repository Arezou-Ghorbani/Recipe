package com.example.recipe.data.repository

import com.example.recipe.data.source.LocalDataSource
import com.example.recipe.data.source.RemoteDataSource
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject
/**Created by Arezou-Ghorbani on 12,October,2023,Arezoughorbaniii@gmail.com**/
@ActivityRetainedScoped
class RecipeRepository @Inject constructor(remoteDataSource: RemoteDataSource, localDataSource: LocalDataSource) {
    val remote = remoteDataSource
    val local = localDataSource
}