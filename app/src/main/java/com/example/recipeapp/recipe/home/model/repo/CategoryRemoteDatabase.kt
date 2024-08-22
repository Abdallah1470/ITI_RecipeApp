package com.example.recipeapp.recipe.home.model.repo

import com.example.homerecipe.home.model.CategoriesResponse

interface CategoryRemoteDatabase {

    suspend fun getAllCategory(): CategoriesResponse?

}