package com.example.recipeapp.recipe.model


import com.example.recipeapp.recipe.mealsOfCategory.model.MealsOfCategory
import com.google.gson.annotations.SerializedName

data class Search(
    @SerializedName("meals")
    val meals: List<MealsOfCategory>?
)