package com.example.recipeapp.recipe

import com.example.recipeapp.recipe.model.Meal

interface LocalDataSource {
    suspend fun getDataFromLocal(): List<Meal>

    suspend fun setMeals(meals: List<Meal>)
}