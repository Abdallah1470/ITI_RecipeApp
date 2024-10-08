package com.example.recipeapp.recipe.profile.viewmodel

import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.auth.register.model.UserRepository
import com.example.recipeapp.recipe.model.Meal
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val repository: UserRepository
) : ViewModel() {
    private val _profileImage = MutableLiveData<Int>()
    val profileImage: LiveData<Int> = _profileImage

    init {
        _profileImage.value = 0
    }

    fun setDarkMode(checked: Boolean) {
        AppCompatDelegate.setDefaultNightMode(
            if (checked) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO
        )
    }

    fun changeProfilePicture(userId: Long, image: Int) {
        viewModelScope.launch {
            repository.changeProfilePicture(userId, image)
        }
    }

    fun updateProfileImage(userId: Long) {
        viewModelScope.launch {
            _profileImage.value = repository.getProfileImage(userId)
        }
    }

    fun deleteUser(long: Long) {
        viewModelScope.launch {
            repository.deleteUser(long)
        }
    }
}