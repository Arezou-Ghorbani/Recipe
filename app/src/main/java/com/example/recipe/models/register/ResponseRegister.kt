package com.example.recipe.models.register

import com.google.gson.annotations.SerializedName

/**Created by Arezou-Ghorbani on 12,October,2023,Arezoughorbaniii@gmail.com**/
data class ResponseRegister(
    @SerializedName("hash")
    val hash: String?, // q572587bq2405724q05
    @SerializedName("spoonacularPassword")
    val spoonacularPassword: String?, // meadwith31grapejam
    @SerializedName("username")
    val username: String? // api_123_user
)