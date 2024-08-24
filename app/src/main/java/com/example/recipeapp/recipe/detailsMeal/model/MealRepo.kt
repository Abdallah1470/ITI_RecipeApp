package com.example.recipeapp.recipe.detailsMeal.model

import com.example.recipeapp.recipe.model.Meal

interface MealRepo {
    suspend fun getMeal(id:String):Meal?
}