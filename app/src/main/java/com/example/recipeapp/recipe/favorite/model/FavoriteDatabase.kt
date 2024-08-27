package com.example.recipeapp.recipe.favorite.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Favorite::class], version = 1)
abstract class FavoriteDatabase : RoomDatabase() {

    abstract fun favoriteDao(): FavoriteDao

    companion object {
        @Volatile
        private var insatnce: FavoriteDatabase? = null
        fun getInstance(context: Context): FavoriteDatabase {
            return insatnce ?: synchronized(this){
                insatnce ?: Room.databaseBuilder(
                    context.applicationContext,
                    FavoriteDatabase::class.java, "favoriteUser_database"
                ).build().also { insatnce = it }
            }
        }
    }
}
