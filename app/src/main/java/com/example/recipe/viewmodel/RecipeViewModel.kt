package com.example.recipe.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipe.data.repository.RecipesRepository
import com.example.recipe.models.recipes.ResponseRecipes
import com.example.recipe.utils.Constant
import com.example.recipe.utils.NetworkRequest
import com.example.recipe.utils.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

/**Created by Arezou-Ghorbani on 13,September,2023,ArezouGhorbaniii@gmail.com**/
@HiltViewModel
class RecipeViewModel @Inject constructor(private val repository: RecipesRepository) : ViewModel() {
    //    popularApi
    private fun popularQueries(): HashMap<String, String> {
        val hashMap: HashMap<String, String> = HashMap()
        hashMap[Constant.API_KEY] = Constant.MY_API_KEY
        hashMap[Constant.SORT] = Constant.SORT_VALUE
        hashMap[Constant.ADD_RECIPE_INFORMATION] = Constant.ADD_RECIPE_INFORMATION_VALUE
        return hashMap
    }

    //    Api
    val popularliveData = MutableLiveData<NetworkRequest<ResponseRecipes>>()
    fun callPopularApi() = viewModelScope.launch {
        popularliveData.value = NetworkRequest.Loading()
        var response = repository.remote.getRecipes(popularQueries())
        popularliveData.value = NetworkResponse(response).generalNetworkResponse()
    }

    //    RecentApi
    private fun recentRecipes(): HashMap<String, String> {
        val hashMap: HashMap<String, String> = HashMap()
        hashMap[Constant.API_KEY] = Constant.MY_API_KEY
        hashMap[Constant.TYPE] = Constant.TYPE_VALUE
        hashMap[Constant.DIET] = Constant.DIET_VALUE
        hashMap[Constant.ADD_RECIPE_INFORMATION] = Constant.ADD_RECIPE_INFORMATION_VALUE
        return hashMap
    }

    //    Api Recent Recipes
    val recentRecipesLiveData = MutableLiveData<NetworkRequest<ResponseRecipes>>()
    fun callRecentApi() = viewModelScope.launch {
        recentRecipesLiveData.value = NetworkRequest.Loading()
        var response = repository.remote.getRecipes(recentRecipes())
        recentRecipesLiveData.value = recentNetworkResponse(response)
    }

    private fun recentNetworkResponse(response: Response<ResponseRecipes>): NetworkRequest<ResponseRecipes> {
        return when {
            response.message().contains("timeout") -> NetworkRequest.Error("Timeout")
            response.code() == 401 -> NetworkRequest.Error("You are not authorized")
            response.code() == 402 -> NetworkRequest.Error("your free limitation plan has finished")
            response.code() == 422 -> NetworkRequest.Error("Api Key not found")
            response.code() == 500 -> NetworkRequest.Error("Try Again")
            response.body()!!.results.isNullOrEmpty() -> NetworkRequest.Error("Not Found Any Recipes!")
            response.isSuccessful -> NetworkRequest.Success(response.body()!!)
            else -> NetworkRequest.Error(response.message())
        }
    }

}