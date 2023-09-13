package com.example.recipe.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.recipe.data.repository.RecipesRepository
import com.example.recipe.models.recipes.ResponseRecipes
import com.example.recipe.utils.NetworkRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**Created by Arezou-Ghorbani on 13,September,2023,ArezouGhorbaniii@gmail.com**/
@HiltViewModel
class RecipeViewModel @Inject constructor(private val repository: RecipesRepository):ViewModel() {
//    popularApi
    val popularliveData=MutableLiveData<NetworkRequest<ResponseRecipes>>()
    fun callPopularApi 271 : 22
}