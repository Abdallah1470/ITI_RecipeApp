package com.example.homerecipe.home.model.repo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.recipeapp.recipe.favorite.model.FavoriteRepository
import com.example.recipeapp.recipe.home.viewModel.HomeViewModel
import com.example.recipeapp.recipe.home.model.repo.CategoryRepositoryImpl

class CategoryViewModelFactory(private val repository: CategoryRepositoryImpl,private val favoriteRepository: FavoriteRepository):ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)){
            return HomeViewModel(repository,favoriteRepository) as T
        }else{
            throw IllegalArgumentException("ViewModel is not found ")
        }
    }
}