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

    // change password
    @Query("UPDATE user SET password = :newPassword WHERE id = :id")
    suspend fun changePassword(id: Long, newPassword: String)

    // change email
    @Query("UPDATE user SET email = :newEmail WHERE id = :id")
    suspend fun changeEmail(id: Long, newEmail: String)

    // change username
    @Query("UPDATE user SET name = :newUsername WHERE id = :id")
    suspend fun changeUsername(id: Long, newUsername: String)

    // change profile picture
    @Query("UPDATE user SET profilePicture = :newProfilePicture WHERE id = :id")
    suspend fun changeProfilePicture(id: Long, newProfilePicture: Int)

    // get profile image
    @Query("SELECT profilePicture FROM user WHERE id = :id")
    suspend fun getProfileImage(id: Long): Int

    // Delete user
    @Query("DELETE FROM user WHERE id = :id")
    suspend fun deleteUser(id: Long)
}