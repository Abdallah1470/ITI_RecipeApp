package com.example.recipeapp.recipe.detailsMeal.model

import com.example.recipeapp.recipe.model.Meal
import com.example.recipeapp.recipe.network.MealsService

class RemoteDataImpl(private val apiService: MealsService):RemoteData {
    override suspend fun getMeal(id: String): Meal {
        return apiService.getMealById(id).meals[0]
    }
}