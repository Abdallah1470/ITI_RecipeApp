package com.example.recipeapp.recipe

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.recipeapp.recipe.model.Meal

/**
 * Data Access Object (DAO) for accessing the meals database.
 */
@Dao
interface MealDao {
    @Query("SELECT * FROM meals")
    suspend fun getAllMeals(): List<Meal>

    @Query("SELECT * FROM meals WHERE idMeal = :id")
    suspend fun getMealById(id: String): Meal

    @Query("SELECT * FROM meals WHERE category = :category")
    suspend fun getMealsByCategory(category: String): List<Meal>

    @Query("SELECT * FROM meals WHERE area = :area")
    suspend fun getMealsByArea(area: String): List<Meal>

    @Query(
        """
        SELECT * FROM meals
        WHERE (:ingredient) IN (ingredient1, ingredient2, ingredient3, ingredient4, ingredient5, 
                                ingredient6, ingredient7, ingredient8, ingredient9, ingredient10, 
                                ingredient11, ingredient12, ingredient13, ingredient14, ingredient15, 
                                ingredient16, ingredient17, ingredient18, ingredient19, ingredient20)
    """
    )
    suspend fun getMealsByIngredient(vararg ingredient: String): List<Meal>

    @Query("SELECT * FROM meals ORDER BY RANDOM() LIMIT 1")
    suspend fun getRandomMeal(): Meal

    @Query("SELECT DISTINCT category FROM meals")
    suspend fun getCategories(): List<String>

    @Query("SELECT DISTINCT area FROM meals")
    suspend fun getAreas(): List<String>

    @Query(
        """
        SELECT DISTINCT ingredient
        FROM (
            SELECT ingredient1 AS ingredient FROM meals
            UNION SELECT ingredient2 FROM meals
            UNION SELECT ingredient3 FROM meals
            UNION SELECT ingredient4 FROM meals
            UNION SELECT ingredient5 FROM meals
            UNION SELECT ingredient6 FROM meals
            UNION SELECT ingredient7 FROM meals
            UNION SELECT ingredient8 FROM meals
            UNION SELECT ingredient9 FROM meals
            UNION SELECT ingredient10 FROM meals
            UNION SELECT ingredient11 FROM meals
            UNION SELECT ingredient12 FROM meals
            UNION SELECT ingredient13 FROM meals
            UNION SELECT ingredient14 FROM meals
            UNION SELECT ingredient15 FROM meals
            UNION SELECT ingredient16 FROM meals
            UNION SELECT ingredient17 FROM meals
            UNION SELECT ingredient18 FROM meals
            UNION SELECT ingredient19 FROM meals
            UNION SELECT ingredient20 FROM meals
        )
        WHERE ingredient IS NOT NULL
        ORDER BY ingredient
    """
    )
    suspend fun getIngredients(): List<String>

    @Query("SELECT * FROM meals WHERE meal_name LIKE '%' || :name || '%'")
    suspend fun searchMealsByName(name: String): List<Meal>

    @Query("DELETE FROM meals")
    suspend fun deleteAllMeals()

    @Insert
    suspend fun addAllMeals(meals: List<Meal>)
}