package com.example.recipeapp.recipe.favorite.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.recipeapp.auth.register.model.UserRepository
import com.example.recipeapp.auth.register.viewmodel.RegisterViewModel
import com.example.recipeapp.recipe.favorite.viewModel.FavoriteViewModel

class FavoriteViewModelFactory(private val repository: FavoriteRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavoriteViewModel::class.java)){
            return FavoriteViewModel(repository) as T
        } else {
            throw IllegalArgumentException("ViewModel is not found ")
        }
    }
}