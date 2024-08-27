package com.example.recipeapp.recipe.favorite.model

import androidx.room.TypeConverter
import com.example.recipeapp.recipe.mealsOfCategory.model.MealsOfCategory
import com.example.recipeapp.recipe.model.Meal
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ConverterResponseMeal {
    @TypeConverter
    fun fromStringList(value: List<Meal>?): String? {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toStringList(value: String?): List<Meal>? {
        val listType = object : TypeToken<List<Meal>>() {}.type
        return Gson().fromJson(value, listType)
    }
}