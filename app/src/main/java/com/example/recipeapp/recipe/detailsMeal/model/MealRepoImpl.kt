package com.example.recipeapp.recipe.detailsMeal.model

import com.example.recipeapp.recipe.model.Meal

class MealRepoImpl(private val repo: RemoteDataImpl) : MealRepo {
    override suspend fun getMeal(id: String): Meal {
        return repo.getMeal(id)
    }
}