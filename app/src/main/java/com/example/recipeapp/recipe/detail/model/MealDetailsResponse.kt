package com.example.recipeapp.recipe.detail.model

import com.example.recipeapp.recipe.model.Meal

data class MealDetailsResponse (
    val meals: List<Meal> = arrayListOf()

)