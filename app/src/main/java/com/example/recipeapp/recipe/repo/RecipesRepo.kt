package com.example.recipeapp.recipe.repo

import com.example.recipeapp.recipe.mealsOfCategory.model.MealsOfCategory
import com.example.recipeapp.recipe.model.Meal


interface RecipesRepo {
    suspend fun getRecipes(): List<Meal>
    suspend fun searchRecipes(query: String): List<MealsOfCategory>
}