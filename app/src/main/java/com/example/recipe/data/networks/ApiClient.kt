package com.example.recipe.data.networks

import com.example.recipe.models.register.BodyRegister
import com.example.recipe.models.register.ResponseRegister
import com.example.recipe.utils.Constant.API_KEY
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiClient {
    @POST("users/connect")
    suspend fun postRegister(@Query(API_KEY) api:String,@Body body:BodyRegister): Response<ResponseRegister>
}