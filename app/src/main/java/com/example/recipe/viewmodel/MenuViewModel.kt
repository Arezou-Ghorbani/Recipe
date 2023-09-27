package com.example.recipe.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipe.data.repository.MenuRepository
import com.example.recipe.data.repository.RecipesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**Created by Arezou-Ghorbani on 25,September,2023,ArezouGhorbaniii@gmail.com**/
@HiltViewModel
class MenuViewModel @Inject constructor(private val repository: MenuRepository) : ViewModel() {
    val readMenuStoredItems = repository.readMenuData

    fun mealsList(): MutableList<String> {
        return mutableListOf(
            "Main Course",
            "Bread",
            "Marinade",
            "Side Dish",
            "Breakfast",
            "Dessert",
            "Soup",
            "Snack",
            "Appetizer",
            "Beverage",
            "Drink",
            "Salad",
            "Sauce"
        )
    }
    fun dietsList(): MutableList<String> {
        return mutableListOf(
            "Gluten Free",
            "Ketogenic",
            "Vegetarian",
            "Vegan",
            "Pescetarian",
            "Paleo"
        )
    }
    fun saveToStore(meal: String, mealId: Int, diet: String, dietId: Int) = viewModelScope.launch(
        Dispatchers.IO) {
        repository.saveMenuData(meal, mealId, diet, dietId)
    }

}