package com.example.recipeapp.recipe.favorite.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.recipe.favorite.model.Favorite
import com.example.recipeapp.recipe.favorite.model.FavoriteRepository
import com.example.recipeapp.recipe.mealsOfCategory.model.MealsOfCategory
import kotlinx.coroutines.launch

class FavoriteViewModel(private val repository: FavoriteRepository) : ViewModel() {

    private val _favoriteResultMutableLiveData = MutableLiveData<MutableList<MealsOfCategory>?>()
    val favoriteLiveData: MutableLiveData<MutableList<MealsOfCategory>?> get() = _favoriteResultMutableLiveData

    fun getMealsFavorite(id: Int) {
        viewModelScope.launch {
            _favoriteResultMutableLiveData.postValue(repository.getAllFavorite(id)?.favoriteMales)
        }
    }

    fun deleteFromFavorite(meal: MealsOfCategory, userId: Int) {
        viewModelScope.launch {
            repository.removeMealFromFavorites(userId, meal)
        }
    }
}

