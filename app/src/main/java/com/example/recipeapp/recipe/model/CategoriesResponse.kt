package com.example.recipeapp.recipe.model


import com.google.gson.annotations.SerializedName

/**
 * Data class representing the response for a list of categories.
 *
 * @property meals The list of categories.
 */
data class CategoriesResponse(
    @SerializedName("categories")
    val categories: List<Category?>?
)