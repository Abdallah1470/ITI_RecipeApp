package com.example.recipeapp.recipe.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.recipeapp.recipe.mealsOfCategory.model.MealsOfCategory
import com.example.recipeapp.recipe.model.Meal

@Database(entities = [Meal::class], version = 1)
abstract class MealDatabase : RoomDatabase() {

    abstract fun mealDao(): MealDao

    companion object {
        @Volatile
        private var INSTANCE: MealDatabase? = null

        fun getDatabase(context: Context): MealDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext, MealDatabase::class.java, "meal_database"
                ).build().also { INSTANCE = it }
            }
        }
    }
}