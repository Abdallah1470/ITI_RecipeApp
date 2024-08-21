package com.example.recipeapp.auth.register.viewmodel

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.recipeapp.auth.register.model.User
import com.example.recipeapp.auth.register.model.UserRepository
import kotlinx.coroutines.launch

class RegisterViewModel(private val repository: UserRepository) : ViewModel() {

    private val registrationResultMutableLiveData = MutableLiveData<RegisterResult>()
    val registrationResultLiveData: LiveData<RegisterResult> get() = registrationResultMutableLiveData

    private val registerNavigationMutableLiveData = MutableLiveData<RegisterNavigation>()
    val registerNavigationLiveData: LiveData<RegisterNavigation> get() = registerNavigationMutableLiveData

    fun register(user: User, confirmPassword: String) {
        when {
            user.name.isEmpty() -> registrationResultMutableLiveData.value = RegisterResult.InvalidData(ErrorType.EmptyName)
            user.email.isEmpty() -> registrationResultMutableLiveData.value = RegisterResult.InvalidData(ErrorType.EmptyEmail)
            !isValidEmail(user.email) -> registrationResultMutableLiveData.value = RegisterResult.InvalidData(ErrorType.InvalidEmailFormat)
            user.password.isEmpty() -> registrationResultMutableLiveData.value = RegisterResult.InvalidData(ErrorType.EmptyPassword)
            !checkPassword(user.password, confirmPassword) -> registrationResultMutableLiveData.value = RegisterResult.InvalidData(ErrorType.PasswordMismatch)
            else -> {
                viewModelScope.launch {
                    val result = repository.insertUser(user)
                    when(result) {
                        true -> {
                            registrationResultMutableLiveData.postValue(RegisterResult.RegisterSuccessful)
                            registerNavigationMutableLiveData.postValue(RegisterNavigation.NavigateToHome)
                        }
                        else-> {
                            registrationResultMutableLiveData.postValue(RegisterResult.RegisterError)
                        }
                    }
                }
            }
        }
    }

    private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun checkPassword(password: String, confirmPassword: String): Boolean {
        return password == confirmPassword
    }

}

sealed class RegisterResult {
    object RegisterSuccessful : RegisterResult()
    object RegisterError : RegisterResult()
    data class InvalidData(val error: ErrorType) : RegisterResult()
}

sealed class ErrorType {
    object EmptyEmail : ErrorType()
    object InvalidEmailFormat : ErrorType()
    object EmptyPassword : ErrorType()
    object EmptyName : ErrorType()
    object PasswordMismatch : ErrorType()
}

sealed class RegisterNavigation {
    object NavigateToHome : RegisterNavigation()
    object NavigateToLogin : RegisterNavigation()
}
