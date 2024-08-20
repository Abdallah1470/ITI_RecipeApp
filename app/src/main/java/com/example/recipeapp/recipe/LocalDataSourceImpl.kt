package com.example.recipeapp.recipe

import com.example.recipeapp.recipe.model.Meal


class LocalDataSourceImpl(private val dao: MealDao) : LocalDataSource {
    override suspend fun getDataFromLocal(): List<Meal> {
        return dao.getAllMeals()
    }

    override suspend fun setMeals(meals: List<Meal>) {
        return dao.addAllMeals(meals)
    }
}