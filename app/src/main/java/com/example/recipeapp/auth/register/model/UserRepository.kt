package com.example.recipeapp.auth.register.model


class UserRepository(private val userDao: UserDao) {

    suspend fun insertUser(user: User): Boolean {
        return try {
            val id = userDao.insertUser(user)
            return id > -1  // Returns true if the user was inserted successfully
        } catch (e: Exception) {
            false
        }
    }

    suspend fun login(email: String, password: String): Long {
        return userDao.login(email, password)
    }

    suspend fun getUserById(id: Long): User? {
        return userDao.getUserById(id)
    }

    suspend fun getUserByEmail(email: String): User? {
        return userDao.getUserByEmail(email)
    }

    suspend fun changePassword(id: Long, newPassword: String) {
        userDao.changePassword(id, newPassword)
    }

    suspend fun changeEmail(id: Long, newEmail: String) {
        userDao.changeEmail(id, newEmail)
    }

    suspend fun changeUsername(id: Long, newUsername: String) {
        userDao.changeUsername(id, newUsername)
    }

    suspend fun changeProfilePicture(id: Long, newProfilePicture: Int) {
        userDao.changeProfilePicture(id, newProfilePicture)
    }

    suspend fun getProfileImage(id: Long): Int {
        return userDao.getProfileImage(id)
    }

    suspend fun deleteUser(id: Long) {
        userDao.deleteUser(id)
    }
}
