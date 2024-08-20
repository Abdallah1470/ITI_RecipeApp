package com.example.recipeapp.recipe.repo

import com.example.recipeapp.recipe.model.Meal

interface RemoteDataSource {
    suspend fun getDataFromRemote(): List<Meal>
}