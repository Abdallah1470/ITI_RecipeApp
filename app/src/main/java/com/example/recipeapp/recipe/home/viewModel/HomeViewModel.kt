package com.example.recipeapp.recipe.home.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homerecipe.home.model.Category
import com.example.recipeapp.recipe.favorite.model.FavoriteRepository
import com.example.recipeapp.recipe.home.model.repo.CategoryRepositoryImpl
import com.example.recipeapp.recipe.model.Meal
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

// take object from repository in constructor
class HomeViewModel(private val repository: CategoryRepositoryImpl,private val favoriteRepository: FavoriteRepository) : ViewModel() {

    private val _data = MutableLiveData<List<Category>>()
    val data:LiveData<List<Category>> get() = _data

    private val _recommendedMeal = MutableLiveData<List<Meal>>()
    val recommendedMeal: LiveData<List<Meal>> get() = _recommendedMeal


    fun fetchProducts() {
        viewModelScope.launch {
            _data.postValue(repository.getAllCategories())
            Log.d("main", "${_data.value?.get(0)?.idCategory}")
        }
    }

    fun getMealRecommended() {
        viewModelScope.launch {
            val mealList: MutableList<Meal> = mutableListOf()

            repeat(10) {
                val deferredMeal = async {
                    repository.getRecommended()[0]
                }
                val meal = deferredMeal.await()
                mealList.add(meal)
            }

            _recommendedMeal.postValue(mealList)
            Log.d("main", "${_recommendedMeal.value?.get(0)?.strMeal}")

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