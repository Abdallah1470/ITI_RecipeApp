package com.example.recipeapp.auth.login.viewmodel

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.auth.register.model.UserRepository
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: UserRepository):ViewModel() {
    private val ResultLiveData = MutableLiveData<LoginResult>()
    val loginResultLiveData: LiveData<LoginResult> get() = ResultLiveData

    private val loginNavigationLiveData = MutableLiveData<LoginNavigation>()
    val loginNavigation: LiveData<LoginNavigation> get() = loginNavigationLiveData


    fun login(email:String, password: String) {
        when {
            email.isEmpty() -> ResultLiveData.value = LoginResult.InvalidData(ErrorType.EmptyEmail)
            password.isEmpty() -> ResultLiveData.value = LoginResult.InvalidData(ErrorType.EmptyPassword)
            !isValidEmail(email) -> ResultLiveData.value = LoginResult.InvalidData(ErrorType.InvalidEmailFormat)
            else -> {
                viewModelScope.launch {
                    val result = repository.login(email,password)
                    when(result) {
                        true -> {
                            ResultLiveData.postValue(LoginResult.LoginSuccessful)
                            loginNavigationLiveData.postValue(LoginNavigation.NavigateToHome)
                        }
                        else-> {
                            ResultLiveData.postValue(LoginResult.LoginError)
                        }
                    }
                }
            }
        }
    }

    private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}

sealed class LoginResult {
    object LoginSuccessful : LoginResult()
    object LoginError : LoginResult()
    data class InvalidData(val error: ErrorType) : LoginResult()
}

sealed class ErrorType {
    object EmptyEmail : ErrorType()
    object InvalidEmailFormat : ErrorType()
    object EmptyPassword : ErrorType()
}

sealed class LoginNavigation {
    object NavigateToHome : LoginNavigation()
}