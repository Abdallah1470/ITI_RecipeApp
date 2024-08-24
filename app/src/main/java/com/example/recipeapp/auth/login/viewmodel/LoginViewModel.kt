package com.example.recipeapp.auth.login.viewmodel
import android.content.Context
import android.content.SharedPreferences
import android.util.Log


import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.auth.login.view.IS_LOGGED
import com.example.recipeapp.auth.login.view.USER_ID
import com.example.recipeapp.auth.login.view.userSharedPreferences
import com.example.recipeapp.auth.register.model.UserRepository
import kotlinx.coroutines.launch



class LoginViewModel(private val repository: UserRepository) : ViewModel() {
    private val _resultLiveData = MutableLiveData<LoginResult>()
    val loginLiveData: LiveData<LoginResult> get() = _resultLiveData

    private val loginNavigationLiveData = MutableLiveData<LoginNavigation>()
    val loginNavigation: LiveData<LoginNavigation> get() = loginNavigationLiveData


    fun login(context: Context, email: String, password: String) {
        when {
            email.isEmpty() -> _resultLiveData.value = LoginResult.InvalidData(ErrorType.EmptyEmail)
            password.isEmpty() -> _resultLiveData.value =
                LoginResult.InvalidData(ErrorType.EmptyPassword)

            !isValidEmail(email) -> _resultLiveData.value =
                LoginResult.InvalidData(ErrorType.InvalidEmailFormat)

            else -> {
                viewModelScope.launch {
                    when(val result = repository.login(email,password)) {
                        (-1).toLong() -> {
                            _resultLiveData.postValue(LoginResult.LoginError)
                        }

                        else -> {
                            saveDataForCurrentUser(context, result)
                            Log.d("User id = ", result.toString())
                            _resultLiveData.postValue(LoginResult.LoginSuccessful)
                            loginNavigationLiveData.postValue(LoginNavigation.NavigateToHome)
                            isLogged(true)
                        }
                    }
                }
            }
        }
    }

    private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }


    private fun saveDataForCurrentUser(context: Context, id: Long) {
        userSharedPreferences.edit().putLong(USER_ID, id).apply()

    }

    private fun isLogged( isLogged: Boolean) {
        userSharedPreferences.edit().putBoolean(IS_LOGGED,isLogged).apply()
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