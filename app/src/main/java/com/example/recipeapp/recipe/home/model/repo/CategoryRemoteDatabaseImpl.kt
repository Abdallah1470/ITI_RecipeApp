package com.example.homerecipe.home.model.repo

import com.example.homerecipe.home.model.CategoriesResponse
import com.example.recipeapp.recipe.home.model.repo.CategoryRemoteDatabase
import com.example.recipeapp.recipe.network.MealsRequest
import com.example.recipeapp.recipe.network.MealsService

class CategoryRemoteDatabaseImpl(private val apiService: MealsService) : CategoryRemoteDatabase {

    override suspend fun getAllCategory(): CategoriesResponse {
        return apiService.getAllCategories()
    }

}