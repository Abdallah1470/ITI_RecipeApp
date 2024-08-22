package com.example.homerecipe.home.model

import com.google.gson.annotations.SerializedName


data class CategoriesResponse (
  @SerializedName("categories" )
  var categories : List<Category> = arrayListOf()
)