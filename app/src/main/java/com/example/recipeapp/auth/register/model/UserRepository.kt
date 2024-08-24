package com.example.recipeapp.auth.register.model


class UserRepository(private val userDao: UserDao) {

    suspend fun insertUser(user: User): Boolean {
        return try {
            val id = userDao.insertUser(user)
            return id > 0  // Returns true if the user was inserted successfully
        } catch (e: Exception) {
            false
        }
    }

    suspend fun login(email: String, password: String): Boolean {
        return userDao.login(email, password)
    }

    suspend fun getUserById(id: Long): User? {
        return userDao.getUserById(id)
    }

    suspend fun addFavorite(userId: Long, recipeId: String) {
        val user = userDao.getUserById(userId)
        user?.let {
            val updatedFavorites = user.favorites.toMutableList().apply { add(recipeId) }
            userDao.updateUser(user.copy(favorites = updatedFavorites))
        }

    }

    suspend fun removeFavorite(userId: Long, recipeId: String) {
        val user = userDao.getUserById(userId)
        user?.let {
            val updatedFavorites = user.favorites.toMutableList().apply { remove(recipeId) }
            userDao.updateUser(user.copy(favorites = updatedFavorites))
        }

    }

    suspend fun isFavorite(userId: Long, recipeId: String): Boolean {
        val user = userDao.getUserById(userId)
        return user?.favorites?.contains(recipeId) ?: false
    }
}
