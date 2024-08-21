package com.example.recipeapp.auth.register.model

import androidx.room.PrimaryKey
import androidx.room.TypeConverters

data class UserData(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val email: String,
    val password: String,
    val favoriteMeals: List<String>
)
