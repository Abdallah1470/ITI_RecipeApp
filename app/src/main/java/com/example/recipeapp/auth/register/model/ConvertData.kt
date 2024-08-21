package com.example.recipeapp.auth.register.model

import androidx.room.TypeConverter
import com.example.recipeapp.recipe.model.Meal
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ConvertData {
    @TypeConverter
    fun fromReviewsList(value: List<Meal>?): String? {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toReviewsList(value: String?): List<Meal>? {
        val listType = object : TypeToken<List<Meal>>() {}.type
        return Gson().fromJson(value, listType)
    }
}
