package com.example.recipeapp.recipe.model


import com.google.gson.annotations.SerializedName

/**
 * Data class representing an Area.
 *
 * @property strArea The name of the area.
 */
data class Area(
    @SerializedName("strArea")
    val strArea: String?
)