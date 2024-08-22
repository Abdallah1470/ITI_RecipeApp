package com.example.homerecipe.home.model.repo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.homerecipe.home.viewModel.CategoryViewModel

class CategoryViewModelFactory(private val repository: CategoryRepository):ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CategoryViewModel::class.java)){
            return CategoryViewModel(repository) as T
        }else{
            throw IllegalArgumentException("ViewModel is not found ")
        }
    }
}