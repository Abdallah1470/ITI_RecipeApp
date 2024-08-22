package com.example.recipeapp.auth.login.view

import android.content.Intent
import android.os.Bundle
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
import com.example.recipeapp.auth.login.LoginViewModelFactory
import com.example.recipeapp.auth.login.viewmodel.LoginNavigation
import com.example.recipeapp.auth.login.viewmodel.LoginResult
import com.example.recipeapp.auth.login.viewmodel.LoginViewModel
import com.example.recipeapp.auth.register.model.UserDatabase
import com.example.recipeapp.auth.register.model.UserRepository
import com.example.recipeapp.auth.register.view.RegisterFragment
import com.example.recipeapp.recipe.home.RecipeActivity

class LoginFragment : Fragment() {

    private lateinit var email:EditText
    private lateinit var password:EditText
    private lateinit var loginButton: Button
    private lateinit var registeTextView:TextView
    private lateinit var visabilityPassword:ImageView

    private val loginViewModel: LoginViewModel by viewModels {
        LoginViewModelFactory(UserRepository(UserDatabase.getInstance(requireContext().applicationContext).userDao()))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        email = view.findViewById(R.id.input_field_email)
        password = view.findViewById<EditText?>(R.id.input_password)
        loginButton = view.findViewById(R.id.button_login)
        registeTextView = view.findViewById(R.id.go_to_sign_up)
        visabilityPassword = view.findViewById(R.id.password_create_visibility_toggle)


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        registeTextView.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
        }

        loginButton.setOnClickListener {
            val strEmail = email.text.toString()
            val strPassword = password.text.toString()
            loginViewModel.login(strEmail,strPassword)
        }


        loginViewModel.loginResultLiveData.observe(viewLifecycleOwner) { result ->
            when (result) {
                is LoginResult.LoginSuccessful -> Toast.makeText(context, "Registration successful", Toast.LENGTH_SHORT).show()

                is LoginResult.LoginError -> Toast.makeText(context, "Registration failed", Toast.LENGTH_SHORT).show()

                is LoginResult.InvalidData -> handleInvalidData(result.error)
            }
        }

        loginViewModel.loginNavigation.observe(viewLifecycleOwner) { navigation ->
            when (navigation) {
                LoginNavigation.NavigateToHome -> startActivity(
                    Intent(context, RecipeActivity::class.java)
                )
            }
        }


        visabilityPassword.setOnClickListener {
            RegisterFragment().toggleVisibility(password,visabilityPassword)
        }

    }

    // check input data before Register
    private fun handleInvalidData(error: com.example.recipeapp.auth.login.viewmodel.ErrorType) {
        when (error) {
            com.example.recipeapp.auth.login.viewmodel.ErrorType.EmptyEmail -> email.error = "Email cannot be empty"
            com.example.recipeapp.auth.login.viewmodel.ErrorType.EmptyPassword -> password.error = "Password cannot be empty"
            com.example.recipeapp.auth.login.viewmodel.ErrorType.InvalidEmailFormat -> email.error = "Invalid email format"
        }
    }

}