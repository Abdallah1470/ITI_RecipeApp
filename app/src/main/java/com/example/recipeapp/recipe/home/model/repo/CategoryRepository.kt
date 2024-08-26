package com.example.recipeapp.recipe.home.model.repo
import com.example.homerecipe.home.model.Category

interface CategoryRepository {

    suspend fun getAllCategories(): List<Category>?
}