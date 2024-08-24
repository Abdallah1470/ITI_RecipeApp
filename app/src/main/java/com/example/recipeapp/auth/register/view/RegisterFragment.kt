package com.example.recipeapp.auth.register.view

import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.recipeapp.R
import com.example.recipeapp.recipe.home.RecipeActivity
import com.example.recipeapp.auth.register.model.RegisterViewModelFactory
import com.example.recipeapp.auth.register.model.User
import com.example.recipeapp.auth.register.model.UserDatabase
import com.example.recipeapp.auth.register.model.UserRepository
import com.example.recipeapp.auth.register.viewmodel.ErrorType
import com.example.recipeapp.auth.register.viewmodel.RegisterNavigation
import com.example.recipeapp.auth.register.viewmodel.RegisterResult
import com.example.recipeapp.auth.register.viewmodel.RegisterViewModel


class RegisterFragment : Fragment() {

    private lateinit var email: EditText
    private lateinit var name: EditText
    private lateinit var password: EditText
    private lateinit var passwordConfirm: EditText
    private lateinit var registerButton: Button
    private lateinit var loginTextView: TextView
    private lateinit var visibilityPassword:ImageView
    private lateinit var visibilityConfirmPassword:ImageView


    private val regViewModel: RegisterViewModel by viewModels {
        val userDao = UserDatabase.getInstance(requireContext().applicationContext).userDao()
        RegisterViewModelFactory(UserRepository(userDao = userDao))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_register, container, false)

        // get references
        email = view.findViewById(R.id.input_user_email)
        name = view.findViewById(R.id.input_field_username)
        password = view.findViewById(R.id.password_Sign_up)
        passwordConfirm = view.findViewById(R.id.confirm_password_signup)
        registerButton = view.findViewById(R.id.button_register)
        loginTextView = view.findViewById(R.id.go_to_sign_up)
        visibilityPassword = view.findViewById(R.id.password_create_visibility_toggle)
        visibilityConfirmPassword = view.findViewById(R.id.repet_password_create_visibility_toggle)

        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        registerButton.setOnClickListener {
            val strName = name.text.toString()
            val strEmail = email.text.toString()
            val strPassword = password.text.toString()
            val strConfirmPassword = passwordConfirm.text.toString()
            val user = User(
                name = strName,
                email = strEmail,
                password = strPassword,
                favorites = emptyList()
            )

            regViewModel.register(user, strConfirmPassword)
        }


        regViewModel.registrationResultLiveData.observe(viewLifecycleOwner) { result ->
            when (result) {
                is RegisterResult.RegisterSuccessful -> Toast.makeText(
                    context,
                    "Registration successful",
                    Toast.LENGTH_SHORT
                ).show()

                is RegisterResult.RegisterError -> Toast.makeText(
                    context,
                    "Registration failed",
                    Toast.LENGTH_SHORT
                ).show()

                is RegisterResult.InvalidData -> handleInvalidData(result.error)
            }
        }

        regViewModel.registerNavigationLiveData.observe(viewLifecycleOwner) { navigation ->
            when (navigation) {
                RegisterNavigation.NavigateToHome -> startActivity(
                    Intent(
                        context,
                        RecipeActivity::class.java
                    )
                )

                RegisterNavigation.NavigateToLogin -> findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
            }
        }


        visibilityPassword.setOnClickListener {
            toggleVisibility(password,visibilityPassword)
        }

        visibilityConfirmPassword.setOnClickListener {
            toggleVisibility(passwordConfirm,visibilityConfirmPassword)
        }

    }

    // check input data before Register
    private fun handleInvalidData(error: ErrorType) {
        when (error) {
            ErrorType.EmptyName -> name.error = "Name cannot be empty"
            ErrorType.EmptyEmail -> email.error = "Email cannot be empty"
            ErrorType.InvalidEmailFormat -> email.error = "Invalid email format"
            ErrorType.EmptyPassword -> password.error = "Password cannot be empty"
            ErrorType.PasswordMismatch -> passwordConfirm.error = "Passwords do not match"
        }
    }

    // function to toggle visibility of password
    fun toggleVisibility(password: EditText, visibilityPassword: ImageView) {
        if (password.transformationMethod is PasswordTransformationMethod) {
            // Show password
            password.transformationMethod = HideReturnsTransformationMethod.getInstance()
            visibilityPassword.setImageResource(R.drawable.show_password)
        } else {
            // Hide password
            password.transformationMethod = PasswordTransformationMethod.getInstance()
            visibilityPassword.setImageResource(R.drawable.unshow_pass)
        }
        // Move the cursor to the end of the text
        password.setSelection(password.text.length)
    }
}