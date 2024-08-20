package com.example.recipeapp.auth.login.view

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

class LoginFragment : Fragment() {

    private lateinit var email:EditText
    private lateinit var password:EditText
    private lateinit var loginButton: Button
    private lateinit var registeTextView:TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        email = view.findViewById(R.id.input_field_email)
        password = view.findViewById(R.id.input_password)
        loginButton = view.findViewById(R.id.button_login)
        registeTextView = view.findViewById(R.id.go_to_sign_up)


        //fun login

        registeTextView.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
            findNavController().navigate(action)
        }

        return view
    }

}