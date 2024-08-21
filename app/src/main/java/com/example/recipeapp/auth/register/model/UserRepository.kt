package com.example.recipeapp.auth.register.model

import androidx.room.Transaction
import com.example.recipeapp.recipe.model.Meal

class UserRepository(private val userDao: UserDao) {

    suspend fun insertUser(user: User) :Boolean{
        return try {
            val id = userDao.insertUser(user)
            id > 0  // Returns true if the user was inserted successfully
        } catch (e: Exception) {
            false
        }    }

    suspend fun login(email: String, password:String):Boolean {
        return userDao.login(email,password)
    }

        suspend fun getUserById(id: Long): User? {
            return userDao.getUserById(id)

        }

    @Transaction
    suspend fun insertFavoriteMeal(userId: Long, meal: Meal) {
        // Fetch the user by their ID
        val user = userDao.getUserById(userId)

        // If the user exists, add the meal to their favoriteMeals list
        user?.let {
            val updatedFavoriteMeals = it.favoriteMeals?.toMutableList()?.apply {
                add(meal)
            }

            // Create a new User object with the updated favoriteMeals
            val updatedUser = it.copy(favoriteMeals = updatedFavoriteMeals)

            // Update the user in the database
             userDao.updateUser(updatedUser)
        }
    }
}
