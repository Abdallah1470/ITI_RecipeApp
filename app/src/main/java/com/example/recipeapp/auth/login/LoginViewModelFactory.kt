package com.example.recipeapp.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.recipeapp.auth.login.viewmodel.LoginViewModel
import com.example.recipeapp.auth.register.model.UserRepository
import com.example.recipeapp.auth.register.viewmodel.RegisterViewModel

class LoginViewModelFactory( private val repository: UserRepository ): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)){
            return LoginViewModel(repository) as T
        }else{
            throw IllegalArgumentException("ViewModel is not found ")
        }
    }
}