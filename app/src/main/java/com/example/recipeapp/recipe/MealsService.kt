package com.example.recipeapp.recipe

import com.example.recipeapp.recipe.model.AreasListResponse
import com.example.recipeapp.recipe.model.CategoriesListResponse
import com.example.recipeapp.recipe.model.CategoriesResponse
import com.example.recipeapp.recipe.model.IngredientsListResponse
import com.example.recipeapp.recipe.model.MealsMainIngredient
import com.example.recipeapp.recipe.model.MealsResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Interface defining the API endpoints for the MealsService.
 */
interface MealsService {

    /**
     * Fetches meals by name.
     * @param mealName The name of the meal to search for.
     * @return A [MealsResponse] containing the search results.
     */
    @GET("search.php")
    suspend fun getMealByName(@Query("s") mealName: String): MealsResponse

    /**
     * Fetches meals starting with a specific letter.
     * @param firstLetter The first letter of the meal names to search for.
     * @return A [MealsResponse] containing the search results.
     */
    @GET("search.php")
    suspend fun getMealsStartingWith(@Query("f") firstLetter: String): MealsResponse

    /**
     * Fetches a meal by its ID.
     * @param mealId The ID of the meal to search for.
     * @return A [MealsResponse] containing the search results.
     */
    @GET("lookup.php")
    suspend fun getMealById(@Query("i") mealId: String): MealsResponse

    /**
     * Fetches a random meal.
     * @return A [MealsResponse] containing the search results.
     */
    @GET("random.php")
    suspend fun getRandomMeal(): MealsResponse

    /**
     * Fetches all meal categories.
     * @return A [CategoriesResponse] containing the search results.
     */
    @GET("categories.php")
    suspend fun getAllMealCategories(): CategoriesResponse

    /**
     * Fetches a list of meal categories.
     * @return A [CategoriesListResponse] containing the list of meal categories.
     */
    @GET("list.php?c=list")
    suspend fun getCategoryList(): CategoriesListResponse

    /**
     * Fetches a list of meal areas.
     * @return A [AreasListResponse] containing the list of meal areas.
     */
    @GET("list.php?a=list")
    suspend fun getAreaList(): AreasListResponse

    /**
     * Fetches a list of meal ingredients.
     * @return A [IngredientsListResponse] containing the list of meal ingredients.
     */
    @GET("list.php?i=list")
    suspend fun getIngredientList(): IngredientsListResponse

    /**
     * Fetches meals by a specific ingredient.
     * @param ingredient The ingredient to search for.
     * @return A [MealsMainIngredient] containing the search results.
     */
    @GET("filter.php")
    suspend fun getMealsByIngredient(@Query("i") ingredient: String): MealsMainIngredient

}
