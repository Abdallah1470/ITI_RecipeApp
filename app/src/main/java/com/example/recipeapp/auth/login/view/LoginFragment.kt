package com.example.recipeapp.auth.login.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.recipeapp.R
import com.example.recipeapp.auth.login.viewmodel.LoginViewModelFactory
import com.example.recipeapp.auth.login.viewmodel.LoginNavigation
import com.example.recipeapp.auth.login.viewmodel.LoginResult
import com.example.recipeapp.auth.login.viewmodel.LoginViewModel
import com.example.recipeapp.auth.register.model.UserDatabase
import com.example.recipeapp.auth.register.model.UserRepository
import com.example.recipeapp.recipe.home.view.RecipeActivity

class LoginFragment : Fragment() {

    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var loginButton: Button
    private lateinit var registerTextView: TextView

    private val loginViewModel: LoginViewModel by viewModels {
        LoginViewModelFactory(UserRepository(UserDatabase.getInstance(requireContext().applicationContext).userDao()))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        initializeView(view)

        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        loginButton.setOnClickListener {
            val strEmail = email.text.toString()
            val strPassword = password.text.toString()
            loginViewModel.login(strEmail, strPassword)
        }


        loginViewModel.loginLiveData.observe(viewLifecycleOwner) { result ->
            when (result) {
                is LoginResult.LoginSuccessful -> Toast.makeText(
                    context,
                    "Login successful",
                    Toast.LENGTH_SHORT
                ).show()

                is LoginResult.LoginError -> Toast.makeText(
                    context,
                    "Login failed",
                    Toast.LENGTH_SHORT
                ).show()

                is LoginResult.InvalidData -> handleInvalidData(result.error)
            }
        }

        loginViewModel.loginNavigation.observe(viewLifecycleOwner) { navigation ->
            when (navigation) {
                LoginNavigation.NavigateToHome -> startActivity(
                    Intent(context, RecipeActivity::class.java)
                )

                LoginNavigation.NavigateToRegister -> findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
            }
        }


        registerTextView.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

    }

    private fun initializeView(view: View) {
        email = view.findViewById(R.id.input_field_email)
        password = view.findViewById(R.id.input_password)
        loginButton = view.findViewById(R.id.button_login)
        registerTextView = view.findViewById(R.id.go_to_sign_up)
    }

    // check input data before Register
    private fun handleInvalidData(error: com.example.recipeapp.auth.login.viewmodel.ErrorType) {
        when (error) {
            com.example.recipeapp.auth.login.viewmodel.ErrorType.EmptyEmail -> email.error =
                "Email cannot be empty"

            com.example.recipeapp.auth.login.viewmodel.ErrorType.EmptyPassword -> password.error =
                "Password cannot be empty"

            com.example.recipeapp.auth.login.viewmodel.ErrorType.InvalidEmailFormat -> email.error =
                "Invalid email format"
        }
    }

}