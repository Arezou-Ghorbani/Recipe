package com.example.recipe.data.repository

import com.example.recipe.data.source.RemoteDataSource
import com.example.recipe.models.register.BodyRegister

/**Created by Arezou-Ghorbani on 03,September,2023,ArezouGhorbaniii@gmail.com**/
class RegisterRepository(private val remote: RemoteDataSource) {
    suspend fun postRegister(apiKey: String, body: BodyRegister) = remote.postRegister(apiKey, body)
}