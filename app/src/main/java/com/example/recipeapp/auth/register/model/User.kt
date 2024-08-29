package com.example.recipeapp.auth.register.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.recipeapp.R

@Entity("user")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val email: String,
    var password: String,
    val salt: ByteArray,
    val profilePicture: Int = R.drawable.ic_grapes
)
