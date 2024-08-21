package com.example.recipeapp.recipe.model


import com.google.gson.annotations.SerializedName

/**
 * Data class representing the response for a list of ingredients.
 *
 * @property meals The list of ingredients.
 */
data class IngredientsListResponse(
    @SerializedName("meals")
    val meals: List<Ingredient>?
)