package com.example.homerecipe.home.model.repo

import com.example.homerecipe.home.model.Category


class CategoryRepositoryImpl(private val remoteDataSource: CategoryRemoteDatabaseImpl):
    CategoryRepository {

    override suspend fun getAllCategories(): List<Category>{
        return remoteDataSource.getAllCategory().categories
    }


}