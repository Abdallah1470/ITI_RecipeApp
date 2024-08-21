package com.example.recipeapp.auth.register.model

import android.content.Context
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserDatabase:RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object
    {
        @Volatile
        private var insatnce:UserDatabase? = null
        fun getInstance(context: Context):UserDatabase{
            return insatnce ?: synchronized(this){
                insatnce?: Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,"user_database"
                ).build().also { insatnce = it  }
            }
        }
    }
}