package com.example.recipeapp.recipe.repo

import com.example.recipeapp.recipe.model.Meal


interface RecipesRepo {
    suspend fun getRecipes(): List<Meal>
}