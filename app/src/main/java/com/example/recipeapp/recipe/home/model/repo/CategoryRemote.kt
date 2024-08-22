package com.example.recipeapp.recipe.home.model.repo

import com.example.homerecipe.home.model.CategoriesResponse

interface CategoryRemote {

    suspend fun getAllCategory(): CategoriesResponse?

}