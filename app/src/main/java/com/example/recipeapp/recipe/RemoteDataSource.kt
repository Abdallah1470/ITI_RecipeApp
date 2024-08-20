package com.example.recipeapp.recipe

import com.example.recipeapp.recipe.model.Meal
import com.example.recipeapp.recipe.model.MealBrief

interface RemoteDataSource {
    suspend fun getDataFromRemote(): List<Meal>
}