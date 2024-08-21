package com.example.recipeapp.recipe.model


import com.google.gson.annotations.SerializedName


/**
 * Data class representing a list of meals under a specific area.
 *
 * @property meals A list of MealBrief objects representing the meals in the area.
 */
data class AreaMeals(
    @SerializedName("meals")
    val meals: List<MealBrief>?
)