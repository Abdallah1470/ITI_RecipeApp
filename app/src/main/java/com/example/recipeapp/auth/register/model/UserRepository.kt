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

    suspend fun login(email: String, password: String): Long {
        return userDao.login(email,password)
    }

    suspend fun getUserById(id: Long): User? {
        return userDao.getUserById(id)
    }

    suspend fun getUserByEmail(email: String): User? {
        return userDao.getUserByEmail(email)
    }
}
