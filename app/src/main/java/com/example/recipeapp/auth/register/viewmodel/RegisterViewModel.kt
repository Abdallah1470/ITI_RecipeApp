package com.example.recipeapp.auth.register.viewmodel

import android.app.Dialog
import android.content.Context
import android.util.Patterns
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.R
import com.example.recipeapp.auth.register.model.User
import com.example.recipeapp.auth.register.model.UserRepository
import kotlinx.coroutines.launch
import java.security.MessageDigest
import java.security.SecureRandom

class RegisterViewModel(private val repository: UserRepository) : ViewModel() {
    private lateinit var dialog: Dialog

    private val registrationResultMutableLiveData = MutableLiveData<RegisterResult>()
    val registrationResultLiveData: LiveData<RegisterResult> get() = registrationResultMutableLiveData

    private val registerNavigationMutableLiveData = MutableLiveData<RegisterNavigation>()
    val registerNavigationLiveData: LiveData<RegisterNavigation> get() = registerNavigationMutableLiveData

    fun register(user: User, confirmPassword: String) {
        when {
            user.name.isEmpty() -> registrationResultMutableLiveData.value =
                RegisterResult.InvalidData(ErrorType.EmptyName)

            user.email.isEmpty() -> registrationResultMutableLiveData.value =
                RegisterResult.InvalidData(ErrorType.EmptyEmail)

            !isValidEmail(user.email) -> registrationResultMutableLiveData.value =
                RegisterResult.InvalidData(ErrorType.InvalidEmailFormat)

            user.password.isEmpty() -> registrationResultMutableLiveData.value =
                RegisterResult.InvalidData(ErrorType.EmptyPassword)

            !checkPassword(
                user.password,
                confirmPassword
            ) -> registrationResultMutableLiveData.value =
                RegisterResult.InvalidData(ErrorType.PasswordMismatch)

            else -> {
                viewModelScope.launch {
                    val hashedPassword = hashPassword(user.password, user.salt)
                    user.password = hashedPassword
                    val result = repository.insertUser(user)
                    when (result) {
                        true -> {
                            registrationResultMutableLiveData.postValue(RegisterResult.RegisterSuccessful)
                            registerNavigationMutableLiveData.postValue(RegisterNavigation.NavigateToLogin)
                        }

                        else -> {
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


    fun show_dialog(isSuccess: Boolean, context: Context) {
        // Initialize the dialog object
        dialog = Dialog(context)
        dialog.setContentView(R.layout.dialog_lauout)
        val tittel = dialog.findViewById<TextView>(R.id.titelAlert)
        dialog.window!!.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialog.setCancelable(true)
        dialog.window!!.attributes.windowAnimations = R.style.animation

        // Find Button within the dialog layout
        val exit =
            dialog.findViewById<Button>(R.id.Alertbutton)

        // Find ImageView within the dialog layout
        val image =
            dialog.findViewById<ImageView>(R.id.imageView)

        if (isSuccess) {
            exit.setBackgroundColor(ActivityCompat.getColor(context, R.color.green))
            tittel.text = "Register Success"
            image.setImageResource(R.drawable.success)
        } else {
            tittel.text = "Register Failed"
            image.setImageResource(R.drawable.faild)
        }

        exit.setOnClickListener { dialog.dismiss() }

        // Use requireContext() instead of context to get a valid context
        (context as? FragmentActivity)?.supportFragmentManager?.let {
            dialog.show()
        }
    }

    // Function to generate a random salt
    fun generateSalt(): ByteArray {
        val salt = ByteArray(16)
        SecureRandom().nextBytes(salt)
        return salt
    }

    // Function to hash a password with salt using SHA-256
    private fun hashPassword(password: String, salt: ByteArray): String {
        val md = MessageDigest.getInstance("SHA-256")
        md.update(salt)
        val hashedPassword = md.digest(password.toByteArray())
        // operation is transforming a result of hashing into a hexadecimal string representation
        return hashedPassword.joinToString("") { "%02x".format(it) }

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
    object NavigateToLogin : RegisterNavigation()
}



