package com.example.recipeapp.recipe.mealsOfCategory.model

import com.example.recipeapp.recipe.mealsOfCategory.model.MealsOfCategory
import com.example.recipeapp.recipe.model.Meal
import com.google.gson.annotations.SerializedName

data class ResponseMeal(
    @SerializedName("meals")
    var meals: List<Meal> = arrayListOf()
)