package com.example.recipe.data.network

import com.example.recipe.models.register.BodyRegister
import com.example.recipe.models.register.ResponseRegister
import com.example.recipe.utils.Constant
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

/**Created by Arezou-Ghorbani on 29,August,2023,Arezoughorbaniii@gmail.com**/
interface ApiServices {
    @POST("users/connect")
    suspend fun postRegister(@Query(Constant.API_KEY) api:String, @Body body: BodyRegister): Response<ResponseRegister>
}