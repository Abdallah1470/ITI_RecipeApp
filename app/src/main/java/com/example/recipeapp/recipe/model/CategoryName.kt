package com.example.recipeapp.recipe.model


import com.google.gson.annotations.SerializedName

/**
 * Data class representing a Category Name.
 *
 * @property strCategory The name of the category.
 */
data class CategoryName(
    @SerializedName("strCategory") val strCategory: String
)