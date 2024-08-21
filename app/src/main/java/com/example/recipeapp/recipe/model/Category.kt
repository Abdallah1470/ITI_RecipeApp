package com.example.recipeapp.recipe.model


import com.google.gson.annotations.SerializedName

/**
 * Data class representing a Category.
 *
 * @property idCategory The unique identifier for the category.
 * @property strCategory The name of the category.
 * @property strCategoryDescription The description of the category.
 * @property strCategoryThumb The URL of the category thumbnail image.
 */
data class Category(
    @SerializedName("idCategory")
    val idCategory: String?,
    @SerializedName("strCategory")
    val strCategory: String?,
    @SerializedName("strCategoryDescription")
    val strCategoryDescription: String?,
    @SerializedName("strCategoryThumb")
    val strCategoryThumb: String?
)