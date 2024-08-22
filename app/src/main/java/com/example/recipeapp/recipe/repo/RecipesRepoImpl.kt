package com.example.recipeapp.recipe.repo

import android.content.Context
import com.example.recipeapp.recipe.model.Meal
import java.util.Calendar

class RecipesRepoImpl(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val context: Context
) : RecipesRepo {

    override suspend fun getRecipes(): List<Meal> {
        // Check if local data is available
        val local = localDataSource.getDataFromLocal()

        // If local data is available and up-to-date, return it
        if (local.isNotEmpty() && isDataUpToDate(context)) {
            return local
        }

        return getAndSaveRemoteData()
    }

    private suspend fun getAndSaveRemoteData(): List<Meal> {
        val remote = remoteDataSource.getDataFromRemote()

        if (remote.isEmpty()) {
            saveRecipesLocally(remote)
        }

        return remote
    }

    override suspend fun saveRecipesLocally(meals: List<Meal>) {
        localDataSource.setMeals(meals)
    }


    private fun isDataUpToDate(context: Context): Boolean {
        // Get the current time
        val currentTime = System.currentTimeMillis()

        // Get the last update timestamp
        val lastUpdateTimestamp = DataUpdateManager.getLastUpdateTimestamp(context)

        // Calculate the next 8 AM after the last update
        val calendar = Calendar.getInstance().apply {
            timeInMillis = lastUpdateTimestamp
            add(Calendar.DAY_OF_YEAR, 1)
            set(Calendar.HOUR_OF_DAY, 8)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }
        val nextUpdate = calendar.timeInMillis

        // Check if the current time is past the next 8 AM
        return currentTime >= nextUpdate
    }
}