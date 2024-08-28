package com.example.recipeapp.recipe.search.model.repo

import com.example.recipeapp.recipe.mealsOfCategory.model.MealsOfCategory
import com.example.recipeapp.recipe.model.Meal
import com.example.recipeapp.recipe.network.MealsService

/**
 * Implementation of the RemoteDataSource interface.
 * This class fetches data from a remote service using the provided MealsService.
 *
 * @property service The MealsService used to fetch data from the remote source.
 */
class RemoteDataSourceImpl(private val service: MealsService) : RemoteDataSource {

    /**
     * Fetches data from the remote source.
     * This method retrieves a list of meals by first fetching the category list,
     * then fetching meals by category, and finally fetching detailed meal information by meal ID.
     *
     * @return A list of Meal objects fetched from the remote source.
     */
    override suspend fun getDataFromRemote(): List<Meal> {
        return service.getCategoryList().meals
    }

    /**
     * Searches for meals by name.
     * This method searches for meals by name using the provided query string.
     *
     * @param query The query string to search for.
     * @return A list of Meal objects matching the search query.
     */
    override suspend fun searchMealsFromRemote(query: String): List<MealsOfCategory> {
        return service.getMealByName(query).meals ?: emptyList()
    }
}