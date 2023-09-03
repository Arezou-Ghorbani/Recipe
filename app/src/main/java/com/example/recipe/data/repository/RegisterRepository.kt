package com.example.recipe.data.repository

import com.example.recipe.data.source.RemoteDataSource
import com.example.recipe.models.register.BodyRegister
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject
import javax.inject.Singleton

/**Created by Arezou-Ghorbani on 03,September,2023,ArezouGhorbaniii@gmail.com**/
@ActivityRetainedScoped
class RegisterRepository@Inject constructor(private val remote: RemoteDataSource) {
    suspend fun postRegister(apiKey: String, body: BodyRegister) = remote.postRegister(apiKey, body)
}