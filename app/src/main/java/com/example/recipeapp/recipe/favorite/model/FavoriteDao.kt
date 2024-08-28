package com.example.recipeapp.recipe.favorite.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface FavoriteDao {
    @Query("SELECT * FROM favorite WHERE userId =:id ")
    suspend fun getAllFavorite(id: Int): Favorite?

    @Insert
    suspend fun insertFavorite(meal: Favorite)

    @Update
    suspend fun updateFavorite(favorite: Favorite)

    @Delete
    suspend fun deleteFromFavorite(meal: Favorite)

    @Query("SELECT EXISTS(SELECT 1 FROM favorite WHERE userId = :userId AND :recipeId IN (favoriteMales))")
    suspend fun isFavorite(userId: Int, recipeId: String): Boolean

}