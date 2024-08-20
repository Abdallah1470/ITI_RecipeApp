package com.example.recipeapp.recipe.model

import com.example.recipeapp.recipe.MealsService
import com.example.recipeapp.recipe.RemoteDataSource

class RemoteDataSourceImpl(private val service: MealsService) : RemoteDataSource {
    override suspend fun getDataFromRemote(): List<Meal> {
        val categoriesNames: List<String> = service.getCategoryList().meals.map { it.strCategory }
        val meals: List<Meal> = categoriesNames.flatMap {
            service.getMealsByCategory(it).meals.flatMap {
                service.getMealById(
                    it.idMeal
                ).meals
            }
        }
        return meals
    }
}