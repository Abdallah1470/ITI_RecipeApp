package com.example.recipeapp.recipe.model


import com.google.gson.annotations.SerializedName

/**
 * Data class representing the response for meals.
 *
 * @property meals The list of meals returned in the response.
 */
data class MealsResponse(
    @SerializedName("meals")
    val meals: List<Meal>
)