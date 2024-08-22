package com.example.homerecipe.meals.model

import com.example.recipeapp.recipe.mealsOfCategory.model.MealsOfCategory
import com.google.gson.annotations.SerializedName

data class ResponseMeal (
    @SerializedName("meals" )
    var meals : List<MealsOfCategory> = arrayListOf()
)