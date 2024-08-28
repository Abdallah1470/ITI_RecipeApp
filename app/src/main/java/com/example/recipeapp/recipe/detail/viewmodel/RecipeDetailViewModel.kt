package com.example.recipeapp.recipe.detail.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.recipe.favorite.model.FavoriteRepository
import com.example.recipeapp.recipe.model.Meal
import com.example.recipeapp.recipe.network.MealsRequest
import kotlinx.coroutines.launch

class RecipeDetailViewModel(
    private val favoriteRepository: FavoriteRepository ) : ViewModel() {

    private val _meal = MutableLiveData<Meal?>()
    val meal: LiveData<Meal?> = _meal

    private val _isFavorite = MutableLiveData<Boolean>()
    val isFavorite: LiveData<Boolean> = _isFavorite

    private val _youtubeUrl = MutableLiveData<String?>()
    val youtubeUrl: LiveData<String?> = _youtubeUrl

    private val _ingredient = MutableLiveData<String>()
    val ingredient: LiveData<String> = _ingredient

    fun fetchMealDetails(recipeId: String) {
        viewModelScope.launch {
            val meal = MealsRequest.service.getMealById(recipeId).meals.firstOrNull()
            _meal.value = meal
        }
    }

    fun setIngredientsSection(meal: Meal) {
        val ingredientList = listOf(
            meal.strMeasure1 to meal.strIngredient1,
            meal.strMeasure2 to meal.strIngredient2,
            meal.strMeasure3 to meal.strIngredient3,
            meal.strMeasure4 to meal.strIngredient4,
            meal.strMeasure5 to meal.strIngredient5,
            meal.strMeasure6 to meal.strIngredient6,
            meal.strMeasure7 to meal.strIngredient7,
            meal.strMeasure8 to meal.strIngredient8,
            meal.strMeasure9 to meal.strIngredient9,
            meal.strMeasure10 to meal.strIngredient10,
            meal.strMeasure11 to meal.strIngredient11,
            meal.strMeasure12 to meal.strIngredient12,
            meal.strMeasure13 to meal.strIngredient13,
            meal.strMeasure14 to meal.strIngredient14,
            meal.strMeasure15 to meal.strIngredient15,
            meal.strMeasure16 to meal.strIngredient16,
            meal.strMeasure17 to meal.strIngredient17,
            meal.strMeasure18 to meal.strIngredient18,
            meal.strMeasure19 to meal.strIngredient19,
            meal.strMeasure20 to meal.strIngredient20
        )

        _ingredient.value = ingredientList.filter { (measure, ingredient) ->
            !(measure.isNullOrBlank() || ingredient.isNullOrBlank())
        }.joinToString("\n") { (measure, ingredient) -> "•$measure $ingredient" }
    }

    fun setupYouTubeVideo(youtubeUrl: String?) {
        _youtubeUrl.value = youtubeUrl?.let { url ->
            extractYouTubeVideoId(url)?.let { id -> "https://www.youtube.com/embed/$id" }
        }
    }

/*    fun updateFavoriteStatus(isChecked: Boolean, userId: Int) {
        viewModelScope.launch {
            if (isChecked) {
                favoriteRepository.addMealToFavorites(userId, meal)
            } else {
                favoriteRepository.removeMealFromFavorites(userId, meal)
            }
        }
    }*/

    fun inFavorites(userId: Int, recipeID: String){
        viewModelScope.launch {
            _isFavorite.value = favoriteRepository.isFavorite(userId, recipeID)
        }
    }

    private fun extractYouTubeVideoId(url: String): String? {
        val regex =
            "^(?:https?://)?(?:www\\.)?(?:youtube\\.com/(?:[^/\\n\\s]+/.+/|(?:v|e(?:mbed)?)|.*[?&]v=)|youtu\\.be/)([^\"&?/\\s]{11})"
        return Regex(regex, RegexOption.IGNORE_CASE).find(url)?.groupValues?.get(1)
    }
}
