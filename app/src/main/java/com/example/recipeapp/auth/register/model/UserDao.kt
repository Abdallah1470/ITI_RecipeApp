package com.example.recipeapp.auth.register.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDao {

    @Insert
    suspend fun insertUser(user: User): Long

    @Query("SELECT * FROM user WHERE email = :email")
    suspend fun getUserByEmail(email: String): User?

    @Query("SELECT * FROM user WHERE id = :id")
    suspend fun getUserById(id: Long): User?

    @Query("SELECT EXISTS(SELECT * FROM user WHERE email = :email AND password = :password)")
    suspend fun login(email: String, password: String): Boolean

    @Update
    suspend fun updateUser(user: User)

    @Query("SELECT EXISTS(SELECT 1 FROM user WHERE id = :userId AND :recipeId IN (favorites))")
    suspend fun isFavorite(userId: Int, recipeId: String): Boolean

    @Query("UPDATE user SET favorites = favorites || :recipeId WHERE id = :userId")
    suspend fun addFavorite(userId: Int, recipeId: String)

    @Query("UPDATE user SET favorites = (SELECT favorites FROM user WHERE id = :userId) - :recipeId WHERE id = :userId")
    suspend fun removeFavorite(userId: Int, recipeId: String)

}