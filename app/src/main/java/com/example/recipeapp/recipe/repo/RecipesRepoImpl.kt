package com.example.recipeapp.recipe.repo

import android.content.Context
import com.example.recipeapp.recipe.mealsOfCategory.model.MealsOfCategory
import com.example.recipeapp.recipe.model.Meal
import java.util.Calendar

class RecipesRepoImpl(
    private val remoteDataSource: RemoteDataSource, private val context: Context
) : RecipesRepo {

    override suspend fun getRecipes(): List<Meal> {
        return remoteDataSource.getDataFromRemote()
    }

    override suspend fun searchRecipes(query: String): List<MealsOfCategory> {
        return remoteDataSource.searchMealsFromRemote(query)
    }

}