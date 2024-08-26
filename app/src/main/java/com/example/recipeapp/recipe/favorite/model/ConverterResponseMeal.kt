package com.example.recipeapp.recipe.favorite.model

import androidx.room.TypeConverter
import com.example.recipeapp.recipe.mealsOfCategory.model.MealsOfCategory
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ConverterResponseMeal {
    @TypeConverter
    fun fromStringList(value: List<MealsOfCategory>?): String? {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toStringList(value: String?): List<MealsOfCategory>? {
        val listType = object : TypeToken<List<MealsOfCategory>>() {}.type
        return Gson().fromJson(value, listType)
    }
}