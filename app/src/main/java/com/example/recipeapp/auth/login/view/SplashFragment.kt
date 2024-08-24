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
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.airbnb.lottie.LottieAnimationView
import com.example.recipeapp.R
import com.example.recipeapp.databinding.FragmentSplashBinding
import com.example.recipeapp.recipe.home.RecipeActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

const val PREF_NAME = "myPreferences"
const val USER_ID = "id"
const val IS_LOGGED = "logged"
lateinit var userSharedPreferences: SharedPreferences

class SplashFragment : Fragment() {
    private lateinit var binding: FragmentSplashBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_splash, container, false)
        userSharedPreferences = (context?.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE) ?:0) as SharedPreferences

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
                        val login = userSharedPreferences.getBoolean(IS_LOGGED, false)
                        if (!login) {
                            findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
                        } else
                            startActivity(Intent(requireContext().applicationContext,RecipeActivity::class.java))
                    }
                }
            })
        }
    }
}