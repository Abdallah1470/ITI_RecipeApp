package com.example.recipeapp.recipe.favorite.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.recipe.favorite.model.Favorite
import com.example.recipeapp.recipe.favorite.model.FavoriteRepository
import com.example.recipeapp.recipe.mealsOfCategory.model.MealsOfCategory
import com.example.recipeapp.recipe.model.Meal
import kotlinx.coroutines.launch

class FavoriteViewModel(private val repository: FavoriteRepository) : ViewModel() {

    private val _favoriteResultMutableLiveData = MutableLiveData<List<Meal>?>()
    val favoriteLiveData: MutableLiveData<List<Meal>?> get() = _favoriteResultMutableLiveData

    fun getMealsFavorite(id: Int) {
        viewModelScope.launch {
            _favoriteResultMutableLiveData.postValue(repository.getAllFavorite(id)?.favoriteMales)
        }
    }


    fun deleteFromFavorite(meal: Meal, userId: Int) {
        viewModelScope.launch {
            // Remove meal from favorites in the repository
            repository.removeMealFromFavorites(userId, meal)

            // Update the list of favorite meals in the ViewModel after deletion
            val currentList = _favoriteResultMutableLiveData.value
            currentList?.let { list ->
                val updatedList = list.toMutableList()
                updatedList.remove(meal)
                _favoriteResultMutableLiveData.postValue(updatedList)
            }
        }
    }
}

