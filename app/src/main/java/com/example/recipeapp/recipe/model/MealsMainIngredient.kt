package com.example.recipeapp.recipe.model


import com.google.gson.annotations.SerializedName

/**
 * Data class representing the main ingredient meals response.
 *
 * @property meals The list of brief descriptions of meals containing the main ingredient.
 */
data class MealsMainIngredient(
    @SerializedName("meals")
    val meals: List<MealBrief>?
)