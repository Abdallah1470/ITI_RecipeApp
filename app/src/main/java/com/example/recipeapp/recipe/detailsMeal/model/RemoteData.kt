package com.example.recipeapp.recipe.detailsMeal.model

import com.example.recipeapp.recipe.model.Meal

interface RemoteData {
    suspend fun getMeal(id:String):Meal?
}