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
import javax.inject.Inject

/**Created by Arezou-Ghorbani on 13,September,2023,ArezouGhorbaniii@gmail.com**/
@HiltViewModel
class RecipeViewModel @Inject constructor(private val repository: RecipesRepository) : ViewModel() {
    //    popularApi
    private fun fillMap(): HashMap<String, String> {
        val hashMap: HashMap<String, String> = HashMap()
        hashMap[Constant.API_KEY] = Constant.MY_API_KEY
        hashMap[Constant.SORT]=Constant.SORT_VALUE
                hashMap[Constant.ADD_RECIPE_INFORMATION]=Constant.ADD_RECIPE_INFORMATION_Value
        return hashMap
    }

    //    Api
    private val popularliveData = MutableLiveData<NetworkRequest<ResponseRecipes>>()
    fun callPopularApi() = viewModelScope.launch {
        popularliveData.value = NetworkRequest.Loading()
        var response = repository.remote.getRecipes(fillMap())
        popularliveData.value = NetworkResponse(response).generalNetworkResponse()
    }
}