package com.example.homerecipe.home.model

import com.example.recipeapp.recipe.model.Meal
import com.google.gson.annotations.SerializedName

data class CategoryListRsponse (
    @SerializedName("meals" ) var meals : List<Meal> = arrayListOf()
)