package com.example.recipeapp.auth.register.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.recipeapp.R


class RegisterFragment : Fragment() {

    private lateinit var email: EditText
    private lateinit var name: EditText
    private lateinit var password: EditText
    private lateinit var passwordConfirm: EditText
    private lateinit var loginButton: Button
    private lateinit var loginTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_register, container, false)

        email = view.findViewById(R.id.input_user_email)
        name = view.findViewById(R.id.input_field_username)
        password = view.findViewById(R.id.password_Sign_up)
        passwordConfirm = view.findViewById(R.id.confirm_password_signup)
        loginButton = view.findViewById(R.id.button_register)
        loginTextView = view.findViewById(R.id.go_to_sign_up)


        //fun login

        loginTextView.setOnClickListener {
            val action = RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
            findNavController().navigate(action)
        }

        return view
    }

}