package com.example.recipeapp.recipe.mealsOfCategory.model

import com.google.gson.annotations.SerializedName

data class MealsOfCategory(
    @SerializedName("strMeal")
    var strMeal: String? = null,
    @SerializedName("strMealThumb" )
    var strMealThumb : String? = null,
    @SerializedName("idMeal")
    var idMeal: String

)