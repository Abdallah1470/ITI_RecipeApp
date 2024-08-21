package com.example.recipeapp.recipe.model


import com.google.gson.annotations.SerializedName

/**
 * Data class representing the response for a list of areas.
 *
 * @property meals The list of areas.
 */
data class AreasListResponse(
    @SerializedName("meals")
    val meals: List<Area>?
)