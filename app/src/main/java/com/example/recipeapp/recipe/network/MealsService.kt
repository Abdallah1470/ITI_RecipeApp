package com.example.recipeapp.recipe.network

import com.example.homerecipe.home.model.CategoryListRsponse
import com.example.recipeapp.recipe.model.AreaMeals
import com.example.recipeapp.recipe.model.AreasListResponse
import com.example.recipeapp.recipe.model.CategoryMeals
import com.example.recipeapp.recipe.model.IngredientsListResponse
import com.example.recipeapp.recipe.model.MainIngredientMeals
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
    suspend fun getAllCategories(): com.example.homerecipe.home.model.CategoriesResponse

    /**
     * Fetches a list of meal categories.
     * @return A [CategoriesListResponse] containing the list of meal categories.
     */
    @GET("list.php?c=list")
    suspend fun getCategoryList(): CategoryListRsponse

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
     * @return A [MainIngredientMeals] containing the search results.
     */
    @GET("filter.php")
    suspend fun getMealsByIngredient(@Query("i") ingredient: String): MainIngredientMeals

    /**
     * Fetches meals by a specific category.
     *
     * @param category The category to search for.
     * @return A [CategoryMeals] containing the search results.
     */
    @GET("filter.php")
    suspend fun getMealsByCategory(@Query("c") category: String): CategoryMeals

    /**
     * Fetches meals by a specific area.
     *
     * @param area The area to search for.
     * @return An [AreaMeals] containing the search results.
     */
    @GET("filter.php")
    suspend fun getMealsByArea(@Query("a") area: String): AreaMeals
}
