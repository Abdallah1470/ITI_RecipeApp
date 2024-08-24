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

    @Query("SELECT COALESCE((SELECT id FROM user WHERE email = :email AND password = :password), -1)")
    suspend fun login(email: String, password: String): Long

    @Update
    suspend fun updateUser(user: User)

}