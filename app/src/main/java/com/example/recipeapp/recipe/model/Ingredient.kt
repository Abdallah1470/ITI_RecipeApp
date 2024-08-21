package com.example.recipeapp.recipe.model


import com.google.gson.annotations.SerializedName

/**
 * Data class representing an Ingredient.
 *
 * @property idIngredient The unique identifier for the ingredient.
 * @property strDescription The description of the ingredient.
 * @property strIngredient The name of the ingredient.
 * @property strType The type of the ingredient.
 */
data class Ingredient(
    @SerializedName("idIngredient")
    val idIngredient: String?,
    @SerializedName("strDescription")
    val strDescription: String?,
    @SerializedName("strIngredient")
    val strIngredient: String?,
    @SerializedName("strType")
    val strType: String?
)