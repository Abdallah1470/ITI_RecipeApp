package com.example.recipeapp.recipe.profile.viewmodel

import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModel

class ProfileViewModel : ViewModel() {
    fun setDarkMode(checked: Boolean) {
        AppCompatDelegate.setDefaultNightMode(
            if (checked) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO
        )
    }
}