package com.example.recipeapp.recipe.profile.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.recipeapp.auth.register.model.UserRepository
import com.example.recipeapp.recipe.favorite.viewModel.FavoriteViewModel

class ProfileViewModelFactory(private val repository: UserRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProfileViewModel::class.java)){
            return ProfileViewModel(repository) as T
        } else {
            throw IllegalArgumentException("ViewModel is not found ")
        }
    }
}