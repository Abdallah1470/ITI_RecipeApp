package com.example.recipeapp.auth.login.view

import android.animation.AnimatorListenerAdapter
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.example.recipeapp.R
import com.example.recipeapp.databinding.FragmentSplashBinding
import com.example.recipeapp.recipe.home.view.RecipeActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

const val PREF_NAME = "myPreferences"
const val SETTINGS_PREF = "settings"
const val USER_ID = "id"
const val IS_LOGIN = "logged"
const val IS_DARK_MODE = "logged"
lateinit var userSharedPreferences: SharedPreferences
lateinit var settingsSharedPreferences: SharedPreferences

class SplashFragment : Fragment() {
    private lateinit var binding: FragmentSplashBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        settingsSharedPreferences = requireContext().getSharedPreferences(SETTINGS_PREF, Context.MODE_PRIVATE)
        AppCompatDelegate.setDefaultNightMode(
            if (settingsSharedPreferences.getBoolean(IS_DARK_MODE, false)) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO
        )

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_splash, container, false)
        userSharedPreferences = (context?.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
            ?: 0) as SharedPreferences

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lottie.apply {
            playAnimation()
            addAnimatorListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: android.animation.Animator) {
                    lifecycleScope.launch {
                        animate().translationX(1000f).setDuration(1000).start()
                        delay(500)
                        val isLogin = userSharedPreferences.getBoolean(IS_LOGIN, false)
                        if (isLogin) {
                            startActivity(
                                Intent(
                                    requireContext().applicationContext,
                                    RecipeActivity::class.java
                                )
                            )
                            requireActivity().finish()

                        } else {
                            findNavController().navigate(R.id.action_splashFragment_to_loginFragment)

                        }
                    }
                }
            })
        }
    }
}