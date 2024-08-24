package com.example.recipeapp.recipe.detailsMeal.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.recipe.detailsMeal.model.MealRepoImpl
import com.example.recipeapp.recipe.model.Meal
import kotlinx.coroutines.launch

class MealDetailsViewModel(private val repository: MealRepoImpl) : ViewModel() {

    private val _meal = MutableLiveData<Meal>()
    val meal: LiveData<Meal> get() = _meal

    fun fetchMeal(id: String) {
        viewModelScope.launch {
            _meal.postValue(repository.getMeal(id))
            Log.d("main","${_meal.value?.strArea}")
        }
    }
}