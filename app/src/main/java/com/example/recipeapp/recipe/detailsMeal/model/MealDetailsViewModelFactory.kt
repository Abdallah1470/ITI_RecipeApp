package com.example.recipeapp.recipe.detailsMeal.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.recipeapp.recipe.detailsMeal.viewModel.MealDetailsViewModel

class MealDetailsViewModelFactory(private val repository: MealRepoImpl) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MealDetailsViewModel::class.java)){
            return MealDetailsViewModel(repository) as T
        } else {
            throw IllegalArgumentException("ViewModel is not found ")
        }
    }
}