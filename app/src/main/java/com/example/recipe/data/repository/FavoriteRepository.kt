package com.example.recipe.data.repository

import com.example.recipe.data.source.LocalDataSource
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class FavoriteRepository @Inject constructor(localDataSource: LocalDataSource) {
    val local = localDataSource
}