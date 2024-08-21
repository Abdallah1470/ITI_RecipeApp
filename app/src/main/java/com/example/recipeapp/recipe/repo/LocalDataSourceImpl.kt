package com.example.recipeapp.recipe.repo

import DataUpdateManager
import android.content.Context
import com.example.recipeapp.recipe.database.MealDao
import com.example.recipeapp.recipe.model.Meal

class LocalDataSourceImpl(private val dao: MealDao, private val context: Context) :
    LocalDataSource {
    override suspend fun getDataFromLocal(): List<Meal> {
        return dao.getAllMeals()
    }

    override suspend fun setMeals(meals: List<Meal>) {
        dao.addAllMeals(meals)
        DataUpdateManager.setLastUpdateTimestamp(context,System.currentTimeMillis())
    }
}