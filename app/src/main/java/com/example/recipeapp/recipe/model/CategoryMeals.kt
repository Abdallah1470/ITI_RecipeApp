package com.example.recipeapp.recipe.model


import com.google.gson.annotations.SerializedName

/**
 * Data class representing a list of meals under a specific category.
 *
 * @property meals A list of MealBrief objects representing the meals in the category.
 */
data class CategoryMeals(
    @SerializedName("meals")
    val meals: List<MealBrief>
)