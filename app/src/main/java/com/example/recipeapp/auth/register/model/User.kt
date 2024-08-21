package com.example.recipeapp.auth.register.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.recipeapp.recipe.model.Meal

@Entity("user")
@TypeConverters(ConvertData::class)
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val email: String,
    val password: String ,
    val favoriteMeals: List<Meal>? = arrayListOf()
)
