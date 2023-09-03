package com.example.recipe.utils

import retrofit2.Response

/**Created by Arezou-Ghorbani on 03,September,2023,ArezouGhorbaniii@gmail.com**/
open class NetworkResponse<T>(private val response: Response<T>) {
    fun generalNetworkResponse(): NetworkRequest<T> {
        return when {
            response.isSuccessful -> NetworkRequest.Success(response.body()!!)
            response.message().contains("timeout") -> NetworkRequest.Error("Timeout")
            response.code() == 401 -> NetworkRequest.Error("You are not authorized")
            response.code() == 402 -> NetworkRequest.Error("your free limitation plan has finished")
            response.code() == 422 -> NetworkRequest.Error("Api Key not found")
            response.code() == 500 -> NetworkRequest.Error("Try Again")
            else -> NetworkRequest.Error(response.message())
        }
    }
}