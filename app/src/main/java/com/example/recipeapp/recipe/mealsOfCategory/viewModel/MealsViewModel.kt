package com.example.recipeapp.recipe.mealsOfCategory.viewModel

import MealRepositoryImpl
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.recipe.favorite.model.FavoriteRepository
import com.example.recipeapp.recipe.model.Meal
import kotlinx.coroutines.launch

class MealsViewModel(private val repository: MealRepositoryImpl,private val favoriteRepository: FavoriteRepository ,private val name:String):ViewModel() {

    private val _meal = MutableLiveData<List<Meal>>()
    val meal: LiveData<List<Meal>> get() = _meal

    fun fetchMeals() {
        viewModelScope.launch {
            Log.d("main", repository.getMealByCategoryName(name).toString())
            _meal.postValue(repository.getMealByCategoryName(name))
            Log.d("main", _meal.value.toString())
        }
    }

    fun insertToFavorite(meal: Meal, id: Int) {
        viewModelScope.launch {
            favoriteRepository.addMealToFavorites(id, meal)
        }
    }

    fun deleteFromFavorite(meal: Meal, id: Int) {
        viewModelScope.launch {
            favoriteRepository.removeMealFromFavorites(id,meal)
        }
    }
}