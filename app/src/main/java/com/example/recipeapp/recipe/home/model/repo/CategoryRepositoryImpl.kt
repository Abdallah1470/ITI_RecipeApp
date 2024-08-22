package com.example.recipeapp.recipe.home.model.repo

import com.example.homerecipe.home.model.Category
import com.example.homerecipe.home.model.repo.CategoryRemoteImpl
import com.example.homerecipe.home.model.repo.CategoryRepository


class CategoryRepositoryImpl(private val remoteDataSource: CategoryRemoteImpl):
    CategoryRepository {

    override suspend fun getAllCategories(): List<Category>{
        return remoteDataSource.getAllCategory().categories
    }


}