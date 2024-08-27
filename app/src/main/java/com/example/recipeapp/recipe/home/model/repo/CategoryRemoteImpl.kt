package com.example.recipeapp.recipe.home.model.repo

import com.example.homerecipe.home.model.CategoriesResponse
import com.example.recipeapp.recipe.home.model.repo.CategoryRemote
import com.example.recipeapp.recipe.model.Meal
import com.example.recipeapp.recipe.model.MealsResponse
import com.example.recipeapp.recipe.network.MealsService

class CategoryRemoteImpl(private val apiService: MealsService) : CategoryRemote {

    override suspend fun getAllCategory(): CategoriesResponse {
        return apiService.getAllCategories()
    }

    override suspend fun getRecommendedMeal(): MealsResponse{
        return apiService.getRandomMeal()
    }

}