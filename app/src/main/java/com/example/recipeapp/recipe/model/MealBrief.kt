package com.example.recipeapp.recipe.model


import com.google.gson.annotations.SerializedName

/**
 * Data class representing a brief description of a Meal.
 *
 * @property idMeal The unique identifier for the meal.
 * @property strMeal The name of the meal.
 * @property strMealThumb The thumbnail image URL of the meal.
 */
data class MealBrief(
    @SerializedName("idMeal")
    val idMeal: String,
    @SerializedName("strMeal")
    val strMeal: String?,
    @SerializedName("strMealThumb")
    val strMealThumb: String?
)