package com.example.recipe.data.network

import com.example.recipe.models.detail.ResponseDetail
import com.example.recipe.models.detail.ResponseSimilar
import com.example.recipe.models.lucky.ResponseLucky
import com.example.recipe.models.recipes.ResponseRecipes
import com.example.recipe.models.register.BodyRegister
import com.example.recipe.models.register.ResponseRegister
import com.example.recipe.utils.Constants
import com.example.recipe.utils.Constants.API_KEY
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

/**Created by Arezou-Ghorbani on 29,August,2023,Arezoughorbaniii@gmail.com**/
interface ApiServices {
    @POST("users/connect")
    suspend fun postRegister(
        @Query(Constants.API_KEY) api: String,
        @Body body: BodyRegister
    ): Response<ResponseRegister>

    @GET("recipes/complexSearch")
    suspend fun getRecipes(@QueryMap queries: Map<String, String>
    ): Response<ResponseRecipes>


    @GET("recipes/{id}/information")
    suspend fun getDetail(@Path("id") id: Int, @Query(API_KEY) apiKey: String): Response<ResponseDetail>


    @GET("recipes/{id}/similar")
    suspend fun getSimilarRecipes(@Path("id") id: Int, @Query(API_KEY) apiKey: String): Response<ResponseSimilar>

    @GET("recipes/random")
    suspend fun getRandomRecipe(@QueryMap queries: Map<String, String>): Response<ResponseLucky>
}