package com.example.recipeapp.recipe.mealsOfCategory.viewModel

import MealRepositoryImpl
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.recipe.favorite.model.Favorite
import com.example.recipeapp.recipe.favorite.model.FavoriteRepository
import com.example.recipeapp.recipe.mealsOfCategory.model.MealsOfCategory
import com.example.recipeapp.recipe.model.CategoryMeals
import kotlinx.coroutines.launch

class MealsViewModel(private val repository: MealRepositoryImpl,private val favoriteRepository: FavoriteRepository ,private val name:String):ViewModel() {

    private val _meal = MutableLiveData<List<MealsOfCategory>>()
    val meal: LiveData<List<MealsOfCategory>> get() = _meal

    fun fetchMeals() {
        viewModelScope.launch {
            Log.d("main", repository.getMealByCategoryName(name).toString())
            _meal.postValue(repository.getMealByCategoryName(name).meals)
            Log.d("main", _meal.value.toString())
        }
    }

    fun insertToFavorite(meal: MealsOfCategory, id: Int) {
        viewModelScope.launch {
            favoriteRepository.addMealToFavorites(id,meal)
        }
    }

    fun deleteFromFavorite(meal: MealsOfCategory, id: Int) {
        viewModelScope.launch {
            favoriteRepository.removeMealFromFavorites(id,meal)
        }
    }
}