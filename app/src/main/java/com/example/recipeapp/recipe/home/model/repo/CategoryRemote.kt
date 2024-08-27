package com.example.recipeapp.recipe.home.model.repo

import com.example.homerecipe.home.model.CategoriesResponse
import com.example.recipeapp.recipe.model.Meal
import com.example.recipeapp.recipe.model.MealsResponse

interface CategoryRemote {

    suspend fun getAllCategory(): CategoriesResponse?

    suspend fun getRecommendedMeal(): MealsResponse?

}