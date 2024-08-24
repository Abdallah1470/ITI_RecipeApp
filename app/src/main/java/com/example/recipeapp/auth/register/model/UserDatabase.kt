package com.example.recipeapp.auth.register.model

import com.example.recipeapp.auth.register.converters.Converters
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [User::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class UserDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var insatnce: UserDatabase? = null
        fun getInstance(context: Context): UserDatabase {
            return insatnce ?: synchronized(this){
                insatnce ?: Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java, "user_database"
                ).build().also { insatnce = it }
            }
        }
    }

}