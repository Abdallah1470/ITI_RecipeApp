package com.example.homerecipe.home.model.repo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.recipeapp.recipe.home.viewModel.CategoryViewModel
import com.example.recipeapp.recipe.home.model.repo.CategoryRepository

class CategoryViewModelFactory(private val repository: CategoryRepository):ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CategoryViewModel::class.java)){
            return CategoryViewModel(repository) as T
        }else{
            throw IllegalArgumentException("ViewModel is not found ")
        }
    }
}