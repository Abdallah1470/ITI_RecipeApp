package com.example.recipeapp.recipe.favorite.model

import com.example.recipeapp.recipe.model.Meal

class FavoriteRepository(private val favoriteDao: FavoriteDao) {

    suspend fun addMealToFavorites(userId: Int, meal: Meal) {
        // Retrieve the existing Favorite record
        val favorite = favoriteDao.getAllFavorite(userId)

        if (favorite != null) {
            // Add the new meal to the favoriteMales list
            favorite.favoriteMales.add(meal)
            // Update the record in the database
            favoriteDao.updateFavorite(favorite)
        } else {
            // If no favorite exists for this user, create a new one
            val newFavorite = Favorite(userId, mutableListOf(meal))
            favoriteDao.insertFavorite(newFavorite)
        }
    }

    suspend fun getAllFavorite(id: Int): Favorite? {
        return favoriteDao.getAllFavorite(id)
    }

    suspend fun removeMealFromFavorites(userId: Int, meal: Meal) {
        // Retrieve the existing Favorite record
        val favorite = favoriteDao.getAllFavorite(userId)

        if (favorite != null) {
            // Remove the meal from the favoriteMales list
            favorite.favoriteMales.remove(meal)

            // Update or delete the record based on the list size
            if (favorite.favoriteMales.isEmpty()) {
                // If the list is empty, you might want to remove the entire favorite entry
                favoriteDao.deleteFromFavorite(favorite)
            } else {
                // Otherwise, update the record in the database
                favoriteDao.updateFavorite(favorite)
            }
        }
    }

    suspend fun isFavorite(userId: Int, recipeID: String): Boolean {
        val favorite = favoriteDao.getAllFavorite(userId)
        return favorite?.favoriteMales?.any() { it.idMeal == recipeID } ?: false
    }


}