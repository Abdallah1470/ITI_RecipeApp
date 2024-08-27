package com.example.recipeapp.recipe.home.model.repo
import com.example.homerecipe.home.model.Category
import com.example.recipeapp.recipe.model.Meal

interface CategoryRepository {

    suspend fun getAllCategories(): List<Category>?

    suspend fun getRecommended():List<Meal>
}