package com.example.recipeapp.recipe

import com.example.recipeapp.recipe.model.Meal

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
        return service.getCategoryList().meals.flatMap { category ->
            service.getMealsByCategory(category.strCategory).meals.flatMap { mealBrief ->
                service.getMealById(mealBrief.idMeal).meals
            }
        }
    }
}