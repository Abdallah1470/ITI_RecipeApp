package com.example.recipeapp.recipe.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Singleton object that provides the Retrofit service for making API requests to the meal database.
 */
object MealsRequest {
    /**
     * Gson instance configured to serialize null values.
     */
    private val gson: Gson = GsonBuilder().serializeNulls().create()

    /**
     * GsonConverterFactory instance for converting JSON responses using Gson.
     */
    private val gsonConverter: GsonConverterFactory = GsonConverterFactory.create(gson)

    /**
     * Retrofit instance configured with the base URL and OkHttpClient.
     */
    private val retrofit = Retrofit.Builder().baseUrl("https://www.themealdb.com/api/json/v1/1/")
        .client(OkHttpClient()).addConverterFactory(
            gsonConverter
        ).build()

    /**
     * Lazy-initialized service instance for making API requests.
     */
    val service: MealsService by lazy { retrofit.create(MealsService::class.java) }
}