package com.example.recipe.data.repository

import com.example.recipe.data.source.LocalDataSource
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject
/**Created by Arezou-Ghorbani on 15,October,2023,ArezouGhorbaniii@gmail.com**/

@ActivityRetainedScoped
class FavoriteRepository @Inject constructor(localDataSource: LocalDataSource) {
    val local = localDataSource
}