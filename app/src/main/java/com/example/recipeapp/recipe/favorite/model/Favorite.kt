package com.example.recipeapp.recipe.favorite.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.recipeapp.recipe.mealsOfCategory.model.MealsOfCategory
import com.example.recipeapp.recipe.model.Meal
import com.google.gson.annotations.SerializedName

@Entity(tableName = "favorite")
@TypeConverters(ConverterResponseMeal::class)
data class Favorite(
    @PrimaryKey(autoGenerate = false)
    @SerializedName("id")
    val userId: Int = 0,
    @SerializedName("favoriteMales")
    val favoriteMales: MutableList<Meal>? = arrayListOf()
)
