package com.example.recipeapp.recipe.search.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.recipe.mealsOfCategory.model.MealsOfCategory
import com.example.recipeapp.recipe.model.Meal
import com.example.recipeapp.recipe.search.model.repo.RecipesRepo
import kotlinx.coroutines.launch

class SearchViewModel(private val repository: RecipesRepo) : ViewModel() {
    private val _searchResults = MutableLiveData<List<MealsOfCategory>>()
    val searchResults: LiveData<List<MealsOfCategory>> = _searchResults

    fun searchRecipes(query: String) {
        viewModelScope.launch {
            _searchResults.value = repository.searchRecipes(query)
        }
    }
}