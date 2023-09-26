package com.example.recipe.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.recipe.data.database.RecipeEntity
import com.example.recipe.data.repository.MenuRepository
import com.example.recipe.data.repository.RecipesRepository
import com.example.recipe.models.recipes.ResponseRecipes
import com.example.recipe.utils.Constants
import com.example.recipe.utils.NetworkRequest
import com.example.recipe.utils.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

/**Created by Arezou-Ghorbani on 13,September,2023,ArezouGhorbaniii@gmail.com**/
@HiltViewModel
class RecipeViewModel @Inject constructor(   private val repository: RecipesRepository,
                                             private val menuRepository: MenuRepository
) : ViewModel() {
    //   ********  popularApi *************
    fun popularQueries(): HashMap<String, String> {
        val queries: HashMap<String, String> = HashMap()
        queries[Constants.API_KEY] = Constants.MY_API_KEY
        queries[Constants.SORT] = Constants.POPULARITY
        queries[Constants.NUMBER] = Constants.LIMITED_COUNT.toString()
        queries[Constants.ADD_RECIPE_INFORMATION] = Constants.TRUE
        return queries
    }

    //    Api
    val popularliveData = MutableLiveData<NetworkRequest<ResponseRecipes>>()
    fun callPopularApi() = viewModelScope.launch {
        popularliveData.value = NetworkRequest.Loading()
        var response = repository.remote.getRecipes(popularQueries())
        popularliveData.value = NetworkResponse(response).generalNetworkResponse()
//      cashData
        val cache = popularliveData.value?.data
        if (cache != null) {
            offlinePopular(cache)
        }
    }

    // local popular
    private fun savePopularResponse(entity: RecipeEntity) =
        viewModelScope.launch(Dispatchers.IO) {
            repository.local.saveRecipes(entity)
        }

    val readPopularFromDb = repository.local.loadRecipes().asLiveData()
    private fun offlinePopular(response: ResponseRecipes) {
        val entity = RecipeEntity(0, response)
        savePopularResponse(entity)
    }

    //   ******** RecentApi **********
    private var mealType = Constants.MAIN_COURSE
    private var dietType = Constants.GLUTEN_FREE
    fun recentQueries(): HashMap<String, String> {
        viewModelScope.launch {
            menuRepository.readMenuData.collect {
                mealType = it.meal
                dietType = it.diet
            }
        }
        val queries: HashMap<String, String> = HashMap()
        queries[Constants.API_KEY] = Constants.MY_API_KEY
        queries[Constants.TYPE] = mealType
        queries[Constants.DIET] = dietType
        queries[Constants.NUMBER] = Constants.FULL_COUNT.toString()
        queries[Constants.ADD_RECIPE_INFORMATION] = Constants.TRUE
        return queries
    }


    //    Api Recent Recipes
    val recentRecipesLiveData = MutableLiveData<NetworkRequest<ResponseRecipes>>()
    fun callRecentApi() = viewModelScope.launch {
        recentRecipesLiveData.value = NetworkRequest.Loading()
        var response = repository.remote.getRecipes(recentQueries())
        recentRecipesLiveData.value = recentNetworkResponse(response)
//   cash
        val cache = popularliveData.value?.data
        if (cache != null) {
            offlineRecent(cache)
        }
    }

//    recentCash

    private fun saveRecentResponse(entity: RecipeEntity) =
        viewModelScope.launch(Dispatchers.IO) {
            repository.local.saveRecipes(entity)
        }

    private fun offlineRecent(response: ResponseRecipes) {
        val entity = RecipeEntity(1, response)
        saveRecentResponse(entity)
    }

    val readRecentFromDb = repository.local.loadRecipes().asLiveData()

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