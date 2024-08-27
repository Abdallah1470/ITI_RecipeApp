package com.example.recipeapp.recipe.home.model.repo

import com.example.homerecipe.home.model.Category
import com.example.recipeapp.recipe.model.Meal


class CategoryRepositoryImpl(private val remoteDataSource: CategoryRemoteImpl):
    CategoryRepository {

    override suspend fun getAllCategories(): List<Category>{
        return remoteDataSource.getAllCategory().categories
    }

    override suspend fun getRecommended(): List<Meal> {
        return remoteDataSource.getRecommendedMeal().meals
    }


}