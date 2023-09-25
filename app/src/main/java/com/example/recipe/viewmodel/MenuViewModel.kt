package com.example.recipe.viewmodel

import androidx.lifecycle.ViewModel
import com.example.recipe.data.repository.RecipesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**Created by Arezou-Ghorbani on 25,September,2023,ArezouGhorbaniii@gmail.com**/
@HiltViewModel
class MenuViewModel @Inject constructor() : ViewModel() {
    fun mealsList(): MutableList<String> {
        return mutableListOf(
            "Main Course", "Bread", "Marinade", "Side Dish", "Breakfast", "Dessert", "Soup", "Snack", "Appetizer",
            "Beverage", "Drink", "Salad", "Sauce"
        )
    }
    fun dietsList(): MutableList<String> {
        return mutableListOf("Gluten Free", "Ketogenic", "Vegetarian", "Vegan", "Pescetarian", "Paleo")
    }
}