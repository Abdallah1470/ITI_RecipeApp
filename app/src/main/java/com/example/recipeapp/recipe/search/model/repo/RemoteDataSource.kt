package com.example.recipeapp.recipe.search.model.repo

import com.example.recipeapp.recipe.mealsOfCategory.model.MealsOfCategory
import com.example.recipeapp.recipe.model.Meal


interface RemoteDataSource {
    suspend fun getDataFromRemote(): List<Meal>
    suspend fun searchMealsFromRemote(query: String): List<MealsOfCategory>

}